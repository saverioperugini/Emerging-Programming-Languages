//*******************************************************************************
//
//      filename:  server.kt
//
//   description:  Executioner module
//
//        author:  Schwartz, Jacob T.
//       Copyright (c) 2019 Schwartz, Jacob T. University of Dayton
//
//******************************************************************************

import java.io.File
import java.util.concurrent.TimeUnit

object Executioner {
    fun run(command: String) {
        command.runCommand()
    }

    private fun String.runCommand(workingDir: File = File("lib/")) {
        ProcessBuilder(*split(" ").toTypedArray())
            .directory(workingDir)
            .redirectOutput(ProcessBuilder.Redirect.INHERIT)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()
            .waitFor(10, TimeUnit.SECONDS)
    }
}

