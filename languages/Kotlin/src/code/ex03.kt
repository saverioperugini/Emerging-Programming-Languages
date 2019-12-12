//*******************************************************************************
//
//      filename:  ex03.kt
//
//   description:  Kotlin Example
//
//        author:  Schwartz, Jacob T
//       Copyright (c) 2019 Jacob Schwartz, University of Dayton
//******************************************************************************

open class Truck(var wheelCount: Int) {
	fun getNumOfWheels(): Int {
		return this.wheelCount
	}
}

var pickup = Truck(4)

print(pickup.getNumOfWheels()) // Prints 4

fun Truck.changeWheelCount(newWheelCount: Int) {
	this.wheelCount = newWheelCount
}

pickup.changeWheelCount(6)

print(pickup.getNumOfWheels()) // Prints 6