//*******************************************************************************
//
//      filename:  ex10.kt
//
//   description:  Kotlin Example
//
//        author:  Schwartz, Jacob T
//       Copyright (c) 2019 Jacob Schwartz, University of Dayton
//******************************************************************************

class Str(var data: String) {
	operator fun divAssign(i: Int) {
		data = data.substring(0..i)
	}
}

var helloWorld = Str("Hello, World!")
helloWorld /= 4
print(helloWorld.data) // Prints Hello