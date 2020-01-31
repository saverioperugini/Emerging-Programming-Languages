/*******************************************************************************
/
/      filename:  Hash.kt
/
/       description:  The hash subcommand
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
import java.security.NoSuchAlgorithmException
import java.util.*
import kotlin.system.exitProcess

@Command(name = "hash", mixinStandardHelpOptions = true,
        description = ["Hashes files and saves them to be checked against in the future"])
class Hash : Runnable {

    @Parameters(index = "0", description = ["The name of where to save the hash"])
    private lateinit var name: String

    @Parameters(index = "1", description = ["The file to hash"])
    private lateinit var file: File

    @Option(names = ["-a", "--algorithm"],
            description = ["The algorithm to use in the hash. Supports all MessageDigest instances"],
            defaultValue = "MD5")
    private var algorithm = "MD5"

    @Option(names = ["-t", "--timestamp"], description = ["Include the timestamp in the hash"], defaultValue = "false")
    private var includeTimestamp = false

    @Option(names = ["-w", "--whitespace"], description = ["Include whitespace in the hash"], defaultValue = "false")
    private var includeWhitespace = false

    override fun run() {
        setup()
        if (!file.exists()) {
            System.err.println("The file does not exist")
            exitProcess(1)
        } else {
            val results = handleFileOrDir(file)
            writeResultToSettings(results.toMutableMap())
        }
    }

    /**
     * writes to the json settings file
     * @param pathHashPairs The hash map pairs to insert into the json
     */
    private fun writeResultToSettings(pathHashPairs: MutableMap<String, String>) {
        val settingsJson = json.parse(SettingsFile.serializer(), settingsFile.readText())
        val currOptions = Options(includeWhitespace, includeTimestamp, algorithm)
        if (settingsJson.hashes.containsKey(name)) {
            if (settingsJson.hashes[name]!!.options != currOptions) {
                System.err.println("If the specified name already exists the options must be the same")
                System.err.println("config options: ${settingsJson.hashes[name]!!.options}")
                System.err.println("command options: $currOptions")
                exitProcess(1)
            } else {
                settingsJson.hashes[name]!!.files.putAll(pathHashPairs)
            }
        } else {
            settingsJson.hashes[name] = HashStore(pathHashPairs, currOptions)
        }
        settingsFile.writeText(json.stringify(SettingsFile.serializer(), settingsJson))
    }

    /**
     * Handles a file or directory. Recurses through directories
     * @param f File to search
     * @return The map of paths to hashes
     */
    private fun handleFileOrDir(f: File): Map<String, String> {
        return runBlocking {
            val fileQueue = LinkedList<File>()
            fileQueue.push(f)

            val jobList = mutableMapOf<String, Deferred<String>>()

            while (fileQueue.isNotEmpty()) {
                val currFile = fileQueue.pop()
                if (currFile.isFile) {
                    jobList[currFile.absolutePath] = async { handleFile(currFile) }
                } else if (currFile.isDirectory) {
                    fileQueue.addAll(currFile.listFiles()!!)
                }
            }

            println("Hashing...")
            return@runBlocking jobList.map { (key, value) ->
                key to value.await()
            }.toMap()
        }
    }

    /**
     * Handles an inidividual file and creates the hash
     * @param f The file to hash
     * @return the hash
     */
    private fun handleFile(f: File): String {
        var text = f.readText()
        if (includeTimestamp) {
            text += f.lastModified().toString()
        }
        if (!includeWhitespace) {
            text = text.replace("\\s+".toRegex(), "")
        }
        return MessageDigest.getInstance(algorithm)
                .digest(text.toByteArray())
                .fold("") { str, it -> str + "%02x".format(it) }
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
        try {
            MessageDigest.getInstance(algorithm)
        } catch (e: NoSuchAlgorithmException) {
            System.err.println("This algorithm is invalid")
            exitProcess(1)
        }
    }
}
