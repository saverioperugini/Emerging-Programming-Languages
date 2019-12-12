//*******************************************************************************
//
//      filename:  ex0.kt
//
//   description:  Kotlin Example
//
//        author:  Schwartz, Jacob T
//       Copyright (c) 2019 Jacob Schwartz, University of Dayton
//******************************************************************************

val nullableList: List<Int?> = listOf(1, 2, null, 4)
val intList: List<Int> = nullableList.filterNotNull()
