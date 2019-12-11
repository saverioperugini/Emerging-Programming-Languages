/*******************************************************************************
/
/      filename:  ListHashes.kt
/
/       description:  The ls subcommand
/
/       author:  McKernan, Thomas A.
/       Copyright (c) 2019 Thomas A. McKernan , University of Dayton
/****************************************************************************/*/
package hasher

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import picocli.CommandLine.Command
import picocli.CommandLine.Parameters
import java.io.File
import kotlin.system.exitProcess

@Command(name = "ls", mixinStandardHelpOptions = true,
        description = ["Lists the available names of saved hashes"])
class ListHashes : Runnable {

    @Parameters(description = ["The name of the hash to retrieve"])
    private var names: MutableList<String> = mutableListOf()

    /**
     * If no args prints a list of hash namespaces
     * If args prints all files for given namespaces
     */
    override fun run() {
        setup()
        val settingsJson = json.parse(SettingsFile.serializer(), settingsFile.readText())

        names.forEach { name ->
            println(
                    settingsJson.hashes[name]?.files?.map { (key, value) ->
                        "$key : $value"
                    }?.joinToString("\n") ?: "There is no hash with this name"
            )
        }
        if (names.isEmpty()) {
            println("Hashes:\n" +
                    settingsJson.hashes.keys.joinToString("\n")
            )
        }
    }


    companion object {
        val settingsResource: String? = System.getenv("HASHER_CONF")
        val json = Json(JsonConfiguration.Stable)
        lateinit var settingsFile: File
    }

    private fun setup() {
        if (settingsResource == null) {
            System.err.println("Please run hasher with no arguments to setup your environment")
            exitProcess(1)
        }
        println("Running from $settingsResource config file\n")
        settingsFile = File(settingsResource)
    }
}
