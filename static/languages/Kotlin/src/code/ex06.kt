//*******************************************************************************
//
//      filename:  ex06.kt
//
//   description:  Kotlin Example
//
//        author:  Schwartz, Jacob T
//       Copyright (c) 2019 Jacob Schwartz, University of Dayton
//******************************************************************************

// No null check
println(x.length) // Throws an Error
// Allow null
println(x?.length) // Prints null
// If null
println(x?.length ?: -1) // Prints -1
// Assert not null println(x!!.length) // Throws an Error