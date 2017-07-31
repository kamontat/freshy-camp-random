package app

import debugger.DHelper
import model.Group
import model.Student
import model.sql.DBUtil

/**
 * @author kamontat
 * @version 1.0
 * @since Sun 30/Jul/2017 - 9:22 AM
 */
class App() {
    private var save: Boolean = false

    init {
        DBUtil.load()
    }

    fun getStudent(studentID: String): List<Student> {
        DHelper.log("search: $studentID")
        return DBUtil.search(studentID)
    }

    fun randomGroup(student: Student): Group {
        save = false
        return DBUtil.getGroup(student)
    }

    fun save() {
        if (save) {
            DHelper.log("already saved")
            return
        }
        save = true
        DBUtil.save()
    }

    fun getSummary(): String {
        return DBUtil.getSummary();
    }
}