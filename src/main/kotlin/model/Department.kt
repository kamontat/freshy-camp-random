package model

import java.util.*

/**
 * @author kamontat
 * @version 1.0
 * @since Fri 28/Jul/2017 - 8:06 PM
 */
enum class Department {
    CPE, SKE;

    companion object {
        fun parse(str: String): Department {
            return valueOf(str.toUpperCase(Locale.ENGLISH))
        }
    }
}