package model

import model.constants.Department
import model.constants.Sex
import java.sql.ResultSet
import java.util.*

/**
 * @author kamontat
 * @version 1.0
 * @since Fri 28/Jul/2017 - 8:02 PM
 */
data class Student(
        val id: Int = 0,
        val student_id: String,
        val title_th: String = "นาย",
        val first_name_th: String = "ไม่รู้",
        val last_name_th: String = "ไม่รู้",
        val title_en: String = "Mr.",
        val first_name_en: String = "unknown",
        val last_name_en: String = "unknown",
        val sex: Sex = Sex.MALE,
        val department: Department = Department.SKE,
        var group: Group? = null,
        val remark: String = "",
        val visibility: Boolean = false) {

    // 0 -> TH
    // 1 -> EN
    var lang = 0;


    override fun equals(other: Any?): Boolean {
        if (other == null || other::class !== Student::class) return false
        return (other as Student).student_id == student_id
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + student_id.hashCode()
        result = 31 * result + title_th.hashCode()
        result = 31 * result + first_name_th.hashCode()
        result = 31 * result + last_name_th.hashCode()
        result = 31 * result + title_en.hashCode()
        result = 31 * result + first_name_en.hashCode()
        result = 31 * result + last_name_en.hashCode()
        result = 31 * result + sex.hashCode()
        result = 31 * result + department.hashCode()
        result = 31 * result + (group?.hashCode() ?: 0)
        result = 31 * result + remark.hashCode()
        result = 31 * result + visibility.hashCode()
        return result
    }

    override fun toString(): String {
        if (lang == 0)
            return "$student_id $title_th $first_name_th $last_name_th - ${department.name.toLowerCase(Locale.ENGLISH)}"
        else
            return "$student_id $title_en $first_name_en $last_name_en - ${department.name.toLowerCase(Locale.ENGLISH)}"

    }

    companion object Builder {
        fun create(result: ResultSet?): Student {
            if (result == null) return Student(student_id = "")
            return Student(
                    result.getInt("ROWID"),
                    result.getString("student_id"),
                    result.getString("title_th"),
                    result.getString("first_name_th"),
                    result.getString("last_name_th"),
                    result.getString("title_en"),
                    result.getString("first_name_en"),
                    result.getString("last_name_en"),
                    Sex.parse(result.getString("sex")),
                    Department.parse(result.getString("department")),
                    Group.parse(result.getString("group")),
                    result.getString("remark"),
                    result.getBoolean("visibility")
            )
        }
    }
}