package model

import java.util.*

/**
 * @author kamontat
 * @version 1.0
 * @since Fri 28/Jul/2017 - 8:14 PM
 */
enum class Sex {
    MALE, FEMALE;

    companion object {
        fun parse(str: String): Sex {
            return when (str.toLowerCase(Locale.ENGLISH)) {
                "นาย" -> MALE
                "male" -> MALE
                "นางสาว" -> FEMALE
                "นาง" -> FEMALE
                "female" -> FEMALE
                else -> MALE
            }
        }
    }
}