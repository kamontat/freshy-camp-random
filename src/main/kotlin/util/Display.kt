package util

import java.awt.Dimension
import java.awt.GraphicsEnvironment
import java.awt.Point
import java.awt.Window

/**
 * @author kamontat
 * @version 1.0
 * @since 17/8/59 - 23:54
 */
object Display {
    /**
     * this variable is display size, <br></br>using by said `"display.getWidth"` and `"display.getHeight"`
     */
    private val display = GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice.displayMode

    /**
     * get point that stay in the center of the screen

     * @param pageSize
     * * 		size of page that want to show in the center
     * *
     * @return point of center screen
     */
    fun getCenterLocation(pageSize: Dimension): Point {
        return Point((display.width / 2 - pageSize.getWidth() / 2).toInt(), (display.height / 2 - pageSize.getHeight() / 2).toInt())
    }

    fun getCenterPage(oldPage: Window?, newPage: Window): Point {
        if (oldPage == null)
            return getCenterLocation(newPage.size)

        val currPoint = oldPage.location

        val newX = currPoint.x + oldPage.width / 2 - newPage.width / 2
        val newY = currPoint.y + oldPage.height / 2 - newPage.height / 2

        return Point(newX, newY)
    }
}