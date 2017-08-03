package main

import api.debugger.Constants
import ui.MainPage
import java.awt.Window

/**
 * @author kamontat
 * @version 1.0
 * @since Sun 30/Jul/2017 - 3:02 PM
 */
fun main(args: Array<String>) {
    Constants.debug = false
    MainPage.run(null as Window?)
}