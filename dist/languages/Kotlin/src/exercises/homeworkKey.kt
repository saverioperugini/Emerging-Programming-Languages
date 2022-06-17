//*******************************************************************************
//
//      filename:  homeworkKey.kt
//
//   description:  Kotlin Homework Key
//
//        author:  Schwartz, Jacob T
//       Copyright (c) 2019 Jacob Schwartz, University of Dayton
//******************************************************************************

fun main() {
	val sports = Table("Sports", "Team", "City", "Game")
	val basketball = UnqTable("NBA Teams", "City", "Team")

	val sportsDB = Database("Sports Database")
	sportsDB += sports
	sportsDB += basketball

	basketball.addRow("Detroit", "Pistons")
	basketball.addRow("Oklahoma City", "Thunder")
	basketball.addRow("Oklahoma City", "Lightning")
	basketball.addRow("Boston", "Celtics")

	println("Line 14:\n${sportsDB.tableNames()}\n")
	println("Line 16:")
	basketball.display(15)


	val cars = UnqTable("Licensed Vehicles", "License Plate", "Make", "Model")
	val carTypes = UnqTable("Types of Cars", "Make", "Model", "Year")

	val automobileDB = Database("Automobiles", carTypes, cars)

	cars.addRow("8UJY76", "Jeep", "Cherokee")
	cars.addRow("56TZ78", "Ford", "Mustang")
	cars.addRow("1QSFI0", "Toyota", "Outback")
	cars.addRow("6THJ89", null, null)

	println("\nLine 28:\n${automobileDB.tableNames()}\n")
	println("Line 29:\nCar with License Plate '56TZ78' is a: ${cars["56TZ78"]}\n")
	println("Line 30:\nCar with License Plate '6THJ89' is a: ${cars["6THJ89"]}\n")

	println("Line 33:\nThere are ${Database.numOfDatabases} database(s)")
}

class Database {
	private val title:String
	var tables = mutableListOf<Table>()

	companion object {
		var numOfDatabases:Int = 0
	}

	constructor(title: String) {
		this.title = title
		numOfDatabases++
	}

	constructor(title: String, vararg initTables:Table) {
		this.title = title
		initTables.forEach{this.tables.add(it)}
		numOfDatabases++
	}

	operator fun plusAssign(table: Table) {
		tables.add(table)
	}

	operator fun minusAssign(table: Table) {
		tables.remove(table)
	}

	fun tableNames():List<String> {
		return tables.map{it.title}
	}
}

open class Table(val title: String, vararg cols:String) {
	private val columnTitles = cols
	protected val numOfColumns = cols.size
	protected var numOfRows = 0
	var data: MutableList<List<String?>> = ArrayList()

	open fun addRow(vararg input:String?):Boolean {
		if (input.size != this.numOfColumns) return false

		return this.data.add(listOf(*input))
	}

	fun display(colSize:Int = 10) {
		for (title in columnTitles) print("| %-${colSize}s ".format(title))

		println("\n${"=".repeat(colSize * numOfColumns)}")

		for (row in data) {
			for (col in row) print("| %-${colSize}s ".format(col!!))
			println()
		}
	}
}

class UnqTable(title:String, vararg cols:String): Table(title, *cols) {

	override fun addRow(vararg input:String?):Boolean {
		if (input.size != this.numOfColumns) return false

		this.data.forEach{if (it[0] == input[0]) return false}

		return this.data.add(listOf(*input))
	}

	operator fun get(key:String):String {
		this.data.forEach{if (it[0] == key) return "${it[1] ?: "Unknown Make"} ${it[2] ?: "Unknown Model"}"}
		return ""
	}
}