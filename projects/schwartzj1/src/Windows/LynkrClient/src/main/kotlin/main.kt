//*******************************************************************************
//
//      filename:  main.kt
//
//   description:  Handles the startup of all program elements and concurrent communication
//
//        author:  Schwartz, Jacob T.
//       Copyright (c) 2019 Schwartz, Jacob T.
//
//******************************************************************************

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

object ChannelManager {
    val access = Channel<String>()
	val keystroke = Channel<String>()
	val media = Channel<String>()
    val power = Channel<String>()
    val volume = Channel<String>()
}

fun main(args: Array<String>) {
    Access; Keystroke; Media; Power; Volume

    GlobalScope.launch {
        while (true) {
            val action = ChannelManager.access.receive()

            println("Access Received Action: $action")

            when (action) {
                "logout" -> Access.logout()
                "lock" -> Access.lock()
                else -> error("Access Cannot Handle: $action")
            }
        }

    }
	
	GlobalScope.launch {
		while (true) {
			val action = ChannelManager.media.receive()
			
			println("Media Received Action: $action")
			
			when (action) {
				"next" -> Media.nextTrack()
				"playpause" -> Media.togglePlay()
				"previous" -> Media.prevTrack()
				else -> error("Media Cannot Handle: $action")
			}
		}
		
	}

    GlobalScope.launch {
	    while (true) {
		    val action = ChannelManager.power.receive()
		
		    println("Power Received Action: $action")
		
		    when (action) {
			    "shutdown" -> Power.shutdown()
			    "restart" -> Power.restart()
			    "hibernate" -> Power.hibernate()
			    else -> error("Power Cannot Handle: $action")
		    }
	    }
	
    }

    GlobalScope.launch {
        while (true) {
            val action = ChannelManager.volume.receive()

            println("Volume Received Action: $action")

            when (action) {
                "increase" -> Volume.increase()
                "decrease" -> Volume.decrease()
                "mute" -> Volume.toggleMute()
                else -> {
	                try {
		                Volume.setLevel(action.toInt())
	                } catch (ex: NumberFormatException) {
		                ex.printStackTrace()
	                }
                }
            }
        }
    }
	
	GlobalScope.launch {
		while (true) {
			val action = ChannelManager.keystroke.receive()
			
			println("Keystroke Received Action: $action")
			
			Keystroke.run(action)
		}
	}

    if (args.isNotEmpty() && args[0] == "manual") {
	    Manual.run()
    } else {
	    BluetoothServer.runServer()
    }
}