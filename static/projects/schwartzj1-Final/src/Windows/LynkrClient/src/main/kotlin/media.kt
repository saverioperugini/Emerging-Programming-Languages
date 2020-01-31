//*******************************************************************************
//
//      filename:  Power.kt
//
//   description:  Power control module
//
//        author:  Schwartz, Jacob T.
//       Copyright (c) 2019 Schwartz, Jacob T.
//
//******************************************************************************

object Media {
	private val controls = mapOf<String, Int>(
		"Next" to 176,
		"Previous" to 177,
		"PlayPause" to 179
	)
	
	fun togglePlay() {
		controls["PlayPause"]?.let { mediaControl(it) }
	}
	
	fun nextTrack() {
		controls["Next"]?.let { mediaControl(it) }
	}
	
	fun prevTrack() {
		controls["Previous"]?.let { mediaControl(it) }
	}
	
	private fun mediaControl(keyCode: Int) {
		Executioner.run("powershell -command \"\$wshShell = new-object -com wscript.shell; \$wshShell.SendKeys([char]$keyCode)\"")
	}
}