package api.debugger

/**
 * @author kamontat
 * @version 1.0
 * @since Fri 28/Jul/2017 - 9:12 PM
 */
object DHelper {
    fun log(str: String) {
        if (Constants.debug) println(str)
    }
}