//*******************************************************************************
//
//      filename:  ex0.1kt
//
//   description:  Kotlin Example
//
//        author:  Schwartz, Jacob T
//       Copyright (c) 2019 Jacob Schwartz, University of Dayton
//******************************************************************************

var onlyMoreThan5: Int = 6
	set(value) {
		if (value > 5) field = value
	}