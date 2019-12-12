/*******************************************************************************
/
/      filename:  SettingsFile.kt
/
/       description:  The Settings file nested objects
/
/       author:  McKernan, Thomas A.
/       Copyright (c) 2019 Thomas A. McKernan , University of Dayton
/****************************************************************************/*/
package hasher

import kotlinx.serialization.Serializable

@Serializable
data class SettingsFile(
        val hashes: MutableMap<String, HashStore>
)

@Serializable
data class HashStore(
        val files: MutableMap<String, String>,
        val options: Options
)

@Serializable
data class Options(
        val includeWhitespace: Boolean,
        val includeTimestamp: Boolean,
        val algorithm: String
)
