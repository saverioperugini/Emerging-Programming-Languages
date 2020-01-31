//*******************************************************************************
//
//      filename:  keystroke.kt
//
//   description:  Keystroke control module
//
//        author:  Schwartz, Jacob T.
//       Copyright (c) 2019 Schwartz, Jacob T.
//
//******************************************************************************

import java.awt.AWTException
import java.awt.Robot
import java.awt.event.KeyEvent
import java.io.File
import java.io.IOError
import java.util.*

object Keystroke {
	private val robot = Robot()
	private val keyMap = mapOf(
		"CONTROL" to KeyEvent.VK_CONTROL,
		"ALT" to KeyEvent.VK_ALT,
		"SHIFT" to KeyEvent.VK_SHIFT,
		"DELETE" to KeyEvent.VK_DELETE,
		"WINDOWS" to KeyEvent.VK_WINDOWS,
		"SPACE" to KeyEvent.VK_SPACE,
		"TILDA" to KeyEvent.VK_DEAD_TILDE,
		"1" to KeyEvent.VK_1,
		"2" to KeyEvent.VK_2,
		"3" to KeyEvent.VK_3,
		"4" to KeyEvent.VK_4,
		"5" to KeyEvent.VK_5,
		"6" to KeyEvent.VK_6,
		"7" to KeyEvent.VK_7,
		"8" to KeyEvent.VK_8,
		"9" to KeyEvent.VK_9,
		"0" to KeyEvent.VK_0,
		"A" to KeyEvent.VK_A,
		"B" to KeyEvent.VK_B,
		"C" to KeyEvent.VK_C,
		"D" to KeyEvent.VK_D,
		"E" to KeyEvent.VK_E,
		"F" to KeyEvent.VK_F,
		"G" to KeyEvent.VK_G,
		"H" to KeyEvent.VK_H,
		"I" to KeyEvent.VK_I,
		"J" to KeyEvent.VK_J,
		"K" to KeyEvent.VK_K,
		"L" to KeyEvent.VK_L,
		"M" to KeyEvent.VK_M,
		"N" to KeyEvent.VK_N,
		"O" to KeyEvent.VK_O,
		"P" to KeyEvent.VK_P,
		"Q" to KeyEvent.VK_Q,
		"R" to KeyEvent.VK_R,
		"S" to KeyEvent.VK_S,
		"T" to KeyEvent.VK_T,
		"U" to KeyEvent.VK_U,
		"V" to KeyEvent.VK_V,
		"W" to KeyEvent.VK_W,
		"X" to KeyEvent.VK_X,
		"Y" to KeyEvent.VK_Y,
		"Z" to KeyEvent.VK_Z,
		"F1" to KeyEvent.VK_F1,
		"F2" to KeyEvent.VK_F2,
		"F3" to KeyEvent.VK_F3,
		"F4" to KeyEvent.VK_F4,
		"F5" to KeyEvent.VK_F5,
		"F6" to KeyEvent.VK_F6,
		"F7" to KeyEvent.VK_F7,
		"F8" to KeyEvent.VK_F8,
		"F9" to KeyEvent.VK_F9,
		"F10" to KeyEvent.VK_F10,
		"F11" to KeyEvent.VK_F11,
		"F12" to KeyEvent.VK_F12
	)
	
	
	fun run(id: String) {
		val press: Queue<Int?> = ArrayDeque()
		val release = Stack<Int?>()
		
		try {
			File("$id.txt").forEachLine { line: String ->
				val key = keyMap[line]
				press.add(key)
				release.push(key)
			}
			
			robot.autoDelay = 250
			while (!press.isEmpty()) press.poll()?.let { robot.keyPress(it) }
			while (release.isNotEmpty()) release.pop()?.let { robot.keyRelease(it) }
			
		} catch (ex: IOError) {
			println("Action not found: $id")
			ex.printStackTrace()
		} catch (ex: AWTException) {
			ex.printStackTrace()
		}
	}
}