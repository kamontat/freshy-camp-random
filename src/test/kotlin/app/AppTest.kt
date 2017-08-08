package app

import model.Group
import model.SPool
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author kamontat
 * *
 * @version 1.0
 * *
 * @since Thu 03/Aug/2017 - 3:15 PM
 */
internal class AppTest {
    private val tempMap = mapOf(
            Group.BLUE to SPool(),
            Group.BROWN to SPool(),
            Group.RED to SPool(),
            Group.GREEN to SPool(),
            Group.YELLOW to SPool(),
            Group.ORANGE to SPool()
    )

    @BeforeEach
    fun setUp() {
        // do nothing
    }

    @AfterEach
    fun tearDown() {
        // do nothing
    }

    @Test
    fun Test1() {
        
    }
}