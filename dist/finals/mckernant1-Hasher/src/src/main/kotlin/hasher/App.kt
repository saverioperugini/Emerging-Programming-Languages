/*******************************************************************************
/
/      filename:  App.kt
/
/       description:  Base command for the CLI
/
/       author:  McKernan, Thomas A.
/       Copyright (c) 2019 Thomas A. McKernan , University of Dayton
/****************************************************************************/*/
package hasher

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import picocli.CommandLine
import picocli.CommandLine.Command
import java.io.File
import java.util.concurrent.Callable
import kotlin.system.exitProcess

@Command(name = "hasher", mixinStandardHelpOptions = true,
        description = ["Stores hashes of given files to check for changes"],
        subcommands = [Hash::class, Check::class, ListHashes::class])
class Hasher : Callable<Int> {
    override fun call(): Int {
        val settingsResource: String? = System.getenv("HASHER_CONF")
        println(settingsResource)
        return if (settingsResource != null) {
            CommandLine.usage(this, System.out)
            1
        } else {
            startUp()
        }
    }

    /**
     * Startup function if the environment variable does not exist
     */
    private fun startUp(): Int {
        println("Welcome to Hasher...")

        println("Your environment variable HASHER_CONF does not exist")
        val defaultPath = "${System.getProperty("user.home")}/.hasher.json"
        print("Please specify your config path or use the default ($defaultPath): ")
        var path = readLine()
        path = if (path.isNullOrBlank()) defaultPath else path
        val settingsFile = File(path)
        if (!settingsFile.exists()) {
            if (!settingsFile.createNewFile()) {
                System.err.println("Could not create file")
                return 1
            }
        }
        val json = Json(JsonConfiguration.Stable)
        settingsFile.writeText(
                json.stringify(SettingsFile.serializer(),
                        SettingsFile(mutableMapOf())))

        println("\n\nLast step! Please add the environment variable to your shell\n")
        println("echo 'export HASHER_CONF=\"${path}\"' >> ~/.bashrc\n")
        println("echo 'export HASHER_CONF=\"${path}\"' >> ~/.zshrc\n")
        println("echo 'export HASHER_CONF=\"${path}\"' >> ~/.kshrc\n")
        return 0
    }
}

fun main(args: Array<String>): Unit = exitProcess(CommandLine(Hasher()).execute(*args))
