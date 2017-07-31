package model

import java.awt.Color
import java.util.*

/**
 * @author kamontat
 * @version 1.0
 * @since Fri 28/Jul/2017 - 9:47 PM
 */
enum class Group(val color: Color) {
    BLUE(Color.BLUE), BROWN(Color(128, 64, 0)), RED(Color.RED), GREEN(Color.GREEN), YELLOW(Color.YELLOW), ORANGE(Color.ORANGE);

    companion object {
        fun parse(str: String?): Group? {
            return str?.toUpperCase(Locale.ENGLISH)?.let { Group.valueOf(it) }
        }
    }
}