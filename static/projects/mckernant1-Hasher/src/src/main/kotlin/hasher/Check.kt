/*******************************************************************************
/
/      filename:  Check.kt
/
/       description:  The check subcommand
/
/       author:  McKernan, Thomas A.
/       Copyright (c) 2019 Thomas A. McKernan , University of Dayton
/****************************************************************************/*/
package hasher

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import picocli.CommandLine.*
import java.io.File
import java.security.MessageDigest
import kotlin.system.exitProcess

@Command(name = "check", mixinStandardHelpOptions = true,
        description = ["Checks hashes of existing files against stored hashes. Shows mismatched files"])
class Check : Runnable {

    @Parameters(index = "0", description = ["Name of the hash to check"])
    private lateinit var name: String

    @Option(names = ["-a", "--all"], description = ["Show all files checked"], defaultValue = "false")
    private var showAllFiles = false

    /**
     * Checks all files in the given hash namespace
     */
    override fun run() {
        setup()
        val settingsJson = json.parse(SettingsFile.serializer(), settingsFile.readText())
        if (settingsJson.hashes[name] == null) {
            System.err.println("There is no hash with this name")
            exitProcess(1)
        }
        runBlocking {
            val jobList = mutableMapOf<String, Deferred<String>>()
            val options = settingsJson.hashes[name]!!.options
            settingsJson.hashes[name]!!.files.forEach { (path, hash) ->
                jobList[path] = async { checkHash(path, hash, options) }
            }

            jobList.forEach { (path, resString) ->
                val res = resString.await()
                if (showAllFiles || res != "Good!") {
                    println("File: $path; Status: $res")
                }
            }
        }
    }

    /**
     * Checks an individual file against its hash
     * @param path The path of the file to check
     * @param hash The previous hash of the file
     * @param options The given options for the hash namespace
     * @return The appropriate string for each case
     */
    private fun checkHash(path: String, hash: String, options: Options): String {
        val f = File(path)
        if (!f.exists()) {
            return "File does not exist"
        }

        var text = f.readText()
        if (options.includeTimestamp) {
            text += f.lastModified().toString()
        }

        if (!options.includeWhitespace) {
            text = text.replace("\\s+".toRegex(), "")
        }
        val digest = MessageDigest.getInstance(options.algorithm)
                .digest(text.toByteArray())
                .fold("") { str, it -> str + "%02x".format(it) }
        if (digest == hash) {
            return "Good!"
        }

        return "Text does not match"
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
        println("Running from ${Hash.settingsResource} config file\n")
        settingsFile = File(settingsResource)
    }
}
