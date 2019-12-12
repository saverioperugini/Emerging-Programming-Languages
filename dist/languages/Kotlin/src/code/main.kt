//*******************************************************************************
//
//      filename:  ex0.kt
//
//   description:  Kotlin Homework Assignment
//
//        author:  Schwartz, Jacob T
//       Copyright (c) 2019 Jacob Schwartz, University of Dayton
//******************************************************************************

fun main(args: Array<String>) {
//fun main(function: () -> Unit) {
	var pickup = Truck(4)
	var semi = Truck(18)

	val americanMeasurements = Measurements({it}, {x: Double -> ((x - 32) * (5/9))})
	val britishMeasurements = Measurements({x: Double -> (x / 2.54)}, {it})
}

open class Truck(var wheels: Int) {

	companion object {
		var totalTrucks = 0
	}

	init {
		totalTrucks++
		println("Truck #$totalTrucks Made")
	}

	var maxTowingWeight = 2000 * wheels

	init {
		println("New Truck has max towing weight of $maxTowingWeight")
	}

	fun getNumOfWheel(): Int {
		return this.wheels
	}
}

data class Measurements(val toInch: (Double) -> Double, val toCelsius: (Double) -> Double)