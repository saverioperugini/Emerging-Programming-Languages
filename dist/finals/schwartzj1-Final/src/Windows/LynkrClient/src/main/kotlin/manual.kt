//*******************************************************************************
//
//      filename:  manual.kt
//
//   description:  Implements a CLI for testing without Bluetooth
//
//        author:  Schwartz, Jacob T.
//       Copyright (c) 2019 Schwartz, Jacob T. University of Dayton
//
//******************************************************************************

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Manual {
    companion object {
        fun run() {
            val manualServer = Manual()
            println("Now accepting input")
            while (true) {
                manualServer.acceptInput()
            }
        }
    }

    private fun handlePayload(payload: String) {
        val payloadTokens = payload.split("|")

        GlobalScope.launch {
            when (payloadTokens[0]) {
                "access" -> ChannelManager.access.send(payloadTokens[1])
	            "keystroke" -> ChannelManager.keystroke.send(payloadTokens[1])
                "power" -> ChannelManager.power.send(payloadTokens[1])
                "volume" -> ChannelManager.volume.send(payloadTokens[1])
                else -> println("Action Item ${payloadTokens[0]} Does Not Exist")
            }
        }
    }

    fun acceptInput() {
        handlePayload(readLine()!!)
    }
}