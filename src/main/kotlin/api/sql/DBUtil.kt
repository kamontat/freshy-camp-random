package api.sql

import api.debugger.DHelper
import model.GPool
import model.Group
import model.SPool
import model.Student
import model.sql.Column
import java.io.File
import java.nio.file.Paths
import java.sql.ResultSet
import java.util.*

/**
 * @author kamontat
 * @version 1.0
 * @since Fri 28/Jul/2017 - 7:45 PM
 */
object DBUtil {
    private val h = Helper("cpsk-60.sqlite", 1)
    private val pool = SPool();

    private val file = Paths.get("").toFile()

    fun load(force: Boolean = false): SPool {
        if (pool.isExist() && !force) return pool

        val re: ResultSet? = h.select("students", Column.Constants.all);
        while (re?.next() ?: false) {
            pool.add(Student.create(re))
        }

        return pool
    }

    fun search(id: String): List<Student> {
        return pool.searchByID(id)
    }

    fun save() {
        var folder = Paths.get(file.absolutePath, "result#0").toFile()
        var i = 1;
        while (folder.exists()) {
            folder = File(folder.parentFile, "result#${i++}")
        }

        GPool.map.forEach {
            group, students ->
            folder.mkdir()
            val newFile = Paths.get(folder.absolutePath, "${group.name}.txt").toFile()
            if (newFile.exists()) {
                DHelper.log("overwrite file: ${newFile.absolutePath}")
                newFile.writeText("${students.listAll()}\n")
            }
            if (newFile.createNewFile()) {
                newFile.appendText("${students.listAll()}\n")
            }
            DHelper.log("save to: ${newFile.absolutePath} ${students.getNumberPer()} person(s)")
        }
    }

    fun getGroup(student: Student): Group {
        val g = GPool.add(student)
        DHelper.log("add $student -> $g")
        return g
    }

    fun getSummary(): String {
        val sb = StringBuilder()
        GPool.map.forEach {
            group, students ->
            if (students.getNumberPer() == 0) {
                sb.append(group.name.toLowerCase(Locale.ENGLISH))
                        .append(" -> ")
                        .append("\n\t")
                        .append("empty")
                        .append("\n")
            } else {
                sb.append(group.name.toLowerCase(Locale.ENGLISH))
                        .append(" -> ")
                        .append("\n\t")
                        .append("Department: ")
                        .append(students.getPercentDep())
                        .append("\n\t")
                        .append("Sex: ")
                        .append(students.getPercentSex())
                        .append("\n")
            }
        }

        return sb.toString()
    }
}