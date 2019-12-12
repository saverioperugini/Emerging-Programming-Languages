//*******************************************************************************
//
//      filename:  keystroke.kt
//
//   description:  Keystroke control module
//
//        author:  Schwartz, Jacob T.
//       Copyright (c) 2019 Schwartz, Jacob T. University of Dayton
//
//******************************************************************************

import java.awt.AWTException
import java.awt.Robot
import java.awt.event.KeyEvent

object Keystroke {
	private val robot = Robot()
	
    fun alpha() {
	    try {
		    robot.autoDelay = 250
		    robot.keyPress(KeyEvent.VK_ALT)
		    robot.keyPress(KeyEvent.VK_C)
		    robot.keyRelease(KeyEvent.VK_C)
		    robot.keyRelease(KeyEvent.VK_ALT)
	    } catch (ex: AWTException) {
		    ex.printStackTrace()
	    }
    }

    fun beta() {
	    try {
		    robot.autoDelay = 250
		    robot.keyPress(KeyEvent.VK_SPACE)
		    robot.keyRelease(KeyEvent.VK_SPACE)
	    } catch (ex: AWTException) {
		    ex.printStackTrace()
	    }
    }

    fun gamma() {
	    try {
		    robot.autoDelay = 250
		    robot.keyPress(KeyEvent.VK_CONTROL)
		    robot.keyPress(KeyEvent.VK_ALT)
		    robot.keyPress(KeyEvent.VK_N)
		    robot.keyRelease(KeyEvent.VK_N)
		    robot.keyRelease(KeyEvent.VK_ALT)
		    robot.keyRelease(KeyEvent.VK_CONTROL)
	    } catch (ex: AWTException) {
		    ex.printStackTrace()
	    }
    }

    fun delta() {
	    try {
		    robot.autoDelay = 250
		    robot.keyPress(KeyEvent.VK_CONTROL)
		    robot.keyPress(KeyEvent.VK_SHIFT)
		    robot.keyPress(KeyEvent.VK_C)
		    robot.keyRelease(KeyEvent.VK_C)
		    robot.keyRelease(KeyEvent.VK_SHIFT)
		    robot.keyRelease(KeyEvent.VK_CONTROL)
	    } catch (ex: AWTException) {
		    ex.printStackTrace()
	    }
    }
}