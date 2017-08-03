package api.sql

/**
 * @param default use in create only
 * @param value new value of column
 * @author kamontat
 * @version 1.0
 * @since Fri 28/Jul/2017 - 6:11 PM
 */
data class Column(val name: String, val type: String = "TEXT", val nullable: Boolean = true, val primaryKey: Boolean = false, val autoIncrement: Boolean = false, private val default: String? = null, private val value: String? = null) {
    fun generate(includeDefault: Boolean = true): String = "\"$name\" $type ${if (primaryKey) "PRIMARY KEY " else ""}${if (!nullable) "NOT NULL" else "NULL"}${if (autoIncrement) " AUTOINCREMENT" else ""}${if (includeDefault && default != null) " DEFAULT ${default()}" else ""}"

    fun default(): String {
        if (default == null) return "\"\""

        try {
            // is integer
            return Integer.parseInt(default).toString()
        } catch (e: NumberFormatException) {
            // is string
            return "\"$default\""
        }
    }

    fun value(): String {
        return "\"$value\""
    }

    object Constants {
        val all = Column("ROWID, *")
    }
}

