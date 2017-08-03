package model

import model.constants.Department
import model.constants.Filter
import model.constants.Sex
import java.util.*

/**
 * @author kamontat
 * @version 1.0
 * @since Sat 29/Jul/2017 - 10:53 PM
 */
class SPool(vararg student: Student) {
    val list: MutableList<Student> = student.toMutableList()

    var person: Int = 0
    var dep: Int = 0
    var sex: Int = 0

    fun add(student: Student) {
        list.remove(student)
        list.add(student)

        updatePerson(1)
        updateDepartment(student)
        updateSex(student)
    }

    private fun updateDepartment(student: Student) {
        // +1 -> SKE
        // -1 -> CPE
        dep += if (student.department == Department.SKE) 1 else -1
    }

    private fun updateSex(student: Student) {
        // +1 -> MALE
        // -1 -> FEMALE
        sex += if (student.sex == Sex.MALE) 1 else -1
    }

    private fun updatePerson(i: Int) {
        person += i
    }

    fun addAll(vararg student: Student) {
        student.forEach(this::add)
    }

    private fun filter(filter: Filter): List<Student> {
        return list.filter(filter.func)
    }

    private fun filterAndCount(filter: Filter): Int {
        return filter(filter).size
    }

    fun getNumberDep(): Int {
        return dep
    }

    fun getNumberSex(): Int {
        return sex
    }

    fun getNumberPer(): Int {
        return person
    }

    fun searchByID(id: String): List<Student> {
        return list.filter { it.group == null && it.student_id.contains(id) }
    }

    fun isExist(): Boolean {
        return list.size > 0
    }

    fun clone(): SPool {
        return SPool(*list.toTypedArray())
    }

    fun listAll(): String {
        val sb = StringBuilder()
        list.forEach { sb.append(it).append("\n") }
        return sb.toString()
    }

    fun getPercentDep(): String {
        val result = (((getNumberPer() + getNumberDep()) * 100) / getNumberPer()) - 100
        if (result < 0) {
            return "CPE have more ${Math.abs(result)}%"
        } else if (result > 0) {
            return "SKE have more ${Math.abs(result)}%"
        }
        return "Balance!"
    }

    fun getPercentSex(): String {
        val result = (((getNumberPer() + getNumberSex()) * 100) / getNumberPer()) - 100
        if (result < 0) {
            return "FEMALE have more ${Math.abs(result)}%"
        } else if (result > 0) {
            return "MALE have more ${Math.abs(result)}%"
        }
        return "Balance!"
    }

    override fun toString(): String {
        return "SPool(list=${Arrays.toString(list.toTypedArray())}, person=$person, dep=$dep, sex=$sex)"
    }
}