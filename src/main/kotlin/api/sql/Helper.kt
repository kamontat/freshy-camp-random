package api.sql

import api.debugger.DHelper
import model.sql.Column
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*

/**
 * @author kamontat
 * @version 1.0
 * @since Fri 28/Jul/2017 - 6:03 PM
 */

open class Helper @Throws(SQLException::class) constructor(name: String, version: Int, private val beatify: Boolean = true) {
    private var connection: Connection = DriverManager.getConnection("jdbc:sqlite:${if (name.indexOf(".") != -1) name else name + ".db"}")

    init {
        connection.createStatement().execute("PRAGMA user_version = $version")
    }

    @Throws(SQLException::class)
    internal fun create(tableName: String, vararg columns: Column): Int {
        val sb = StringBuilder("CREATE TABLE IF NOT EXISTS \"$tableName\" (${addFormat(1)}");
        columns.forEachIndexed { index, column ->
            sb.append(column.generate())
            if (index != columns.size - 1)
                sb.append(", ${addFormat(1)}")
        }
        sb.append("${addFormat()});")
        DHelper.log(sb.toString())
        return connection.createStatement().executeUpdate(sb.toString())
    }

    internal fun select(tableName: String, vararg columns: Column, condition: String? = null): ResultSet? {
        val sb = StringBuilder("SELECT ${addFormat(1)}");
        columns.forEachIndexed { index, (name) ->
            sb.append(name)
            if (index != columns.size - 1)
                sb.append(", ${addFormat(1)}")
        }
        sb.append(" ${addFormat()}FROM \"$tableName\" ")
        if (condition != null) sb.append("${addFormat()}WHERE ${addFormat(1)}" + condition)

        DHelper.log(sb.toString())
        return connection.createStatement().executeQuery(sb.toString())
    }

    internal fun insert(tableName: String, vararg columns: Column): Int {
        val sb = StringBuilder("INSERT INTO \"$tableName\" (");
        columns.forEachIndexed { index, (name) ->
            // ignore id
            if (name.toLowerCase(Locale.ENGLISH) == "rowid" && name.toLowerCase(Locale.ENGLISH) == "id") return@forEachIndexed

            sb.append(name)
            if (index != columns.size - 1)
                sb.append(", ")
            else sb.append(")${addFormat(2)}VALUES (")
        }

        columns.forEachIndexed { index, column ->
            // ignore id
            if (column.name.toLowerCase(Locale.ENGLISH) == "rowid" && column.name.toLowerCase(Locale.ENGLISH) == "id") return@forEachIndexed

            sb.append(column.value())
            if (index != columns.size - 1)
                sb.append(", ")
            else sb.append(");")
        }
        DHelper.log(sb.toString())
        return connection.createStatement().executeUpdate(sb.toString())
    }

    internal fun update(tableName: String, vararg columns: Column, condition: String? = null): Int {
        val sb = StringBuilder("UPDATE \"$tableName\" ${addFormat()}");
        sb.append("SET ${addFormat(1)}")
        columns.forEachIndexed { index, column ->
            sb.append("${column.name} = ${column.value()}")
            if (index != columns.size - 1)
                sb.append(", ${addFormat(1)}")
        }
        if (condition != null) sb.append("${addFormat()}WHERE ${addFormat(1)}" + condition)
        DHelper.log(sb.toString())
        return connection.createStatement().executeUpdate(sb.toString())
    }

    private fun addFormat(indentLevel: Int = 0, newLine: Boolean = true): String = when {
        beatify -> {
            val str: StringBuilder = StringBuilder()
            if (newLine) str.append("\n")
            for (i in 1..indentLevel) {
                str.append("\t")
            }
            str.toString()
        }
        else -> ""
    }
}