//*******************************************************************************
//
//      filename:  volume.kt
//
//   description:  Volume control module
//
//        author:  Schwartz, Jacob T.
//       Copyright (c) 2019 Schwartz, Jacob T. University of Dayton
//
//******************************************************************************

object Volume {
    private var muteState = false

    init {
        changeVol("unmute")
    }

    fun increase(interval: Int = 2) {
        changeVol("+$interval")
    }

    fun decrease(interval: Int = 2) {
        changeVol("-$interval")
    }

    fun toggleMute() {
        if (muteState) {
            changeVol("unmute")
        } else {
            changeVol("mute")
        }

        muteState = !muteState
    }

    private fun changeVol(command: String) {
        val path = "${System.getProperty("user.dir")}\\lib"
        Executioner.run("$path\\SetVol.exe $command")
    }

}