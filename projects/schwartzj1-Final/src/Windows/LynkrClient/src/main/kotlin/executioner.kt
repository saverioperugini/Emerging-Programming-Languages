//*******************************************************************************
//
//      filename:  server.kt
//
//   description:  Executioner module
//
//        author:  Schwartz, Jacob T.
//       Copyright (c) 2019 Schwartz, Jacob T.
//
//******************************************************************************

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit


object Executioner {
	fun run(command: String): List<String> {
		return command.runCommand()
	}
	
	private fun String.runCommand(workingDir: File = File("/")): List<String>{
		val process = ProcessBuilder(*split(" ").toTypedArray())
			.directory(workingDir)
			.start()
		
		val output = mutableListOf<String>()
		BufferedReader(InputStreamReader(process.inputStream)).use { reader ->
			var line: String?
			while (reader.readLine().also { line = it } != null) output.add(line!!)
		}
		
		process.waitFor(10, TimeUnit.SECONDS)
		
		return output
		// 48
	}
}

