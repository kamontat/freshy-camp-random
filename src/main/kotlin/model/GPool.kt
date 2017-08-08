package model

import api.debugger.DHelper
import model.constants.Department
import model.constants.Sex
import api.extension.filterWithNonEmpty
import api.extension.random
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author kamontat
 * @version 1.0
 * @since Fri 28/Jul/2017 - 9:48 PM
 */
object GPool {
    val map = mapOf(
            Group.BLUE to SPool(),
            Group.BROWN to SPool(),
            Group.RED to SPool(),
            Group.GREEN to SPool(),
            Group.YELLOW to SPool(),
            Group.ORANGE to SPool()
    )

    fun add(student: Student): Group {
        map.forEach {
            group: Group, students: SPool ->
            DHelper.log("$group -> $students")
        }

        // first condition: group number
        val gPerson = getMinimumPersonNumber()
        DHelper.log("person -> ${Arrays.toString(gPerson)}")

        // second: department
        val gDepartment = getDepartment(student)
        DHelper.log("department -> ${Arrays.toString(gDepartment)}")

        // third: sex
        val gSex = getSex(student)
        DHelper.log("sex -> ${Arrays.toString(gSex)}")

        if (gPerson.isEmpty() && gDepartment.isEmpty() && gSex.isEmpty()) {
            DHelper.log("(condition) all empty --> random")
            return _add(map.random(), student)
        } else {
            val newMap = map.filterWithNonEmpty {
                entry ->
                gPerson.contains(entry.key)
            }.filterWithNonEmpty {
                entry ->
                gDepartment.contains(entry.key)
            }.filterWithNonEmpty {
                entry ->
                gSex.contains(entry.key)
            }

            DHelper.log("(condition) have ${newMap.size} group left (${Arrays.toString(newMap.keys.toTypedArray())}) --> random")
            return _add(newMap.random(), student)
        }
    }

    private fun _add(group: Group, student: Student): Group {
        map[group]!!.add(student)
        student.group = group
        return group
    }

    private fun getMinimumPersonNumber(): Array<Group> {
        val arr = ArrayList<Group>()
        var min: Pair<Group, SPool>? = null

        map.forEach {
            group, students ->
            if (min == null) {
                arr.remove(group)
                arr.add(group)
                min = group to students
            } else {
                // not real min
                if (min!!.second.getNumberPer() > students.getNumberPer()) {
                    arr.remove(min!!.first)
                    arr.add(group)
                    min = group to students
                } else if (min!!.second.getNumberPer() == students.getNumberPer()) {
                    arr.add(group)
                    min = group to students // update latest max pair
                }
            }
        }

        return arr.toTypedArray()
    }

    private fun getDepartment(student: Student): Array<Group> {
        val arr = ArrayList<Group>()
        var diff: Int = map.values.toTypedArray()[0].getNumberDep();

        map.forEach {
            group, students ->
            DHelper.log("(department) $group :: current: ${students.getNumberDep()}")
            when (student.department) {
                Department.SKE -> {
                    when {
                        Math.abs(students.getNumberDep() + 1) < diff -> {
                            arr.clear()
                            arr.add(group)

                            diff = Math.abs(students.getNumberDep() + 1)
                        }
                        Math.abs(students.getNumberDep() + 1) == diff -> arr.add(group)
                    }
                }
                Department.CPE -> {
                    when {
                        Math.abs(students.getNumberDep() - 1) < diff -> {
                            arr.clear()
                            arr.add(group)

                            diff = Math.abs(students.getNumberDep() + 1)
                        }
                        Math.abs(students.getNumberDep() - 1) == diff -> arr.add(group)
                    }
                }
            }
        }

        return arr.toTypedArray()
    }

    private fun getSex(student: Student): Array<Group> {
        val arr = ArrayList<Group>()
        var diff: Int = Math.abs(map.values.toTypedArray()[0].getNumberSex());

        map.forEach {
            group, students ->
            DHelper.log("(sex) $group :: current: ${students.getNumberSex()} +/- 1 < $diff")
            when (student.sex) {
                Sex.MALE -> {
                    when {
                        Math.abs(students.getNumberSex() + 1) < diff -> {
                            arr.clear()
                            arr.add(group)

                            diff = Math.abs(students.getNumberSex() + 1)
                        }
                        Math.abs(students.getNumberSex() + 1) == diff -> arr.add(group)
                    }
                }
                Sex.FEMALE -> {
                    when {
                        Math.abs(students.getNumberSex() - 1) < diff -> {
                            arr.clear()
                            arr.add(group)

                            diff = Math.abs(students.getNumberSex() + 1)
                        }
                        Math.abs(students.getNumberSex() - 1) == diff -> arr.add(group)
                    }
                }
            }
        }

        return arr.toTypedArray()
    }
}