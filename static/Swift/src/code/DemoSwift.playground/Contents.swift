/*******************************************************************************
 /
 /      filename:  DemoSwift.playground
 /
 /   description:  Implements features of the swift language into a demo of the language
 /
 /        author:  Patnaik, Ajay J.
 /       Copyright (c) 2019 Ajay Patnaik, University of Dayton
********************************************************************************/

import UIKit

// let vs var
let a = 1 // let makes something immutable
var b = 2 // var makes something mutable

//a = 2
//b = 3

// Types
let c = "" // Type inference
type(of: c)

let d: Int = 3 // You can strong type variables
type(of: d)

// let e: Bool = 6 // Gives type error

// Type Identifiers
typealias Point = (Int, Int)
let f = (0, 1)
type(of: f)

// Tuples
// helpful when returning multiple values in a function
let g = (min: 0, max: "ten")
type(of: g)

// Collections
// three types of collections: arrays, sets, and dictionaries
// Arrays
var h = [Int]()
h.append(3)

var i = Array(repeating: 5, count: 4)

var j = h + i

j[2...4] = [1, 2]
j

j.insert(10, at: 0)
j.removeLast()
j

// Sets
var k: Set<Int> = [1, 2, 3, 4, 5, 6]
var l: Set<Int> = [5, 6, 7, 8, 9, 10]

let m = k.intersection(l)
let n = k.subtracting(l)

// Dictionaries
var o = [Int: String]()
o[3] = "Hi"
o[29] = "Hello"
o

let p = [String](o.values)

// Functions
func hello(person: String) -> String {
    return "Hello " + person + "!"
}
hello(person: "Ajay")

// underscore allows you to not have to label the parameter in the call
func greeting(_ person: String) -> String {
    return "Hello " + person + "!"
}
greeting("Ajay")

// you can return multiple values with tuples
func minMax(array: [Int]) -> (min: Int, max: Int) {
    var currentMin = array[0]
    var currentMax = array[0]
    for value in array[1..<array.count] {
        if value < currentMin {
            currentMin = value
        } else if value > currentMax {
            currentMax = value
        }
    }
    return (currentMin, currentMax)
}

let q = minMax(array: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
q.min
q.max
type(of: q)

// A variadic parameter accepts zero or more values of a specified type.
func avg(_ numbers: Double...) -> Double {
    var total: Double = 0
    for number in numbers {
        total += number
    }
    return total / Double(numbers.count)
}

avg(1)
avg(1, 2, 3, 4)
avg(1, 2, 3, 5.4, 3.2, 0.3)

// In-out parameters
var r = 1
var s = 2

func swap(_ a: inout Int, _ b: inout Int) {
    let temp = a
    a = b
    b = temp
}
swap(&r, &s)
r
s

// First Class Functions
func add(_ a: Int, _ b: Int) -> Int {
    return a + b
}

let t: (Int, Int) -> Int = add
t(1, 2)

func printAdd(_ f: (Int, Int) -> Int, _ a: Int, _ b: Int) {
    print("Result: \(f(a, b))")
}
printAdd(add, 1, 2)

func sub(_ a: Int, _ b: Int) -> Int {
    return b - a
}

func returnSub() -> (Int, Int) -> Int {
    return sub
}

returnSub()(1, 2)

// Closures
func makeIncrementer(forIncrement amount: Int) -> () -> Int {
    var runningTotal = 0
    func incrementer() -> Int {
        runningTotal += amount
        return runningTotal
    }
    return incrementer
}

let incTen = makeIncrementer(forIncrement: 10)
let incFour = makeIncrementer(forIncrement: 4)

incTen()
incTen()
incFour()
incTen()
incFour()

// Autoclosures
var customers = ["Ajay", "Tom", "Tyler"]
let serve = { customers.remove(at: 0) }
print("Now Serving: \(serve())")
print("Now Serving: \(serve())")

// Lazy Properties
class Importer {
    var filename = "test.txt"
}

class Manager {
    lazy var importer = Importer()
    var file = "a"
}

let manager = Manager()
manager.file
manager.importer // lazy properties are not created until they are first used

// Computed Properties
struct Square {
    var height: Int
    var width: Int
    var area: Int {
        get {
            return height * width
        }
    }
}

var u = Square(height: 5, width: 10)
u.area

// Cool for debuging and other things
// willSet and didSet
class Counter {
    var inc: Int = 0 {
        willSet(newInc) {
            print("Setting inc to \(newInc)")
        }
        didSet {
            print("Difference between new and old inc is \(inc - oldValue)")
        }
    }
}

let counter = Counter()
counter.inc = 10
counter.inc = 20
counter.inc = 40

// Deinitialization
class Bank {
    static var coinsInBank = 10000
    static func send(coins numCoins: Int) -> Int {
        coinsInBank -= numCoins
        return numCoins
    }
    static func receive(coins: Int) {
        coinsInBank += coins
    }
}

class Person {
    var coinsInPocket: Int
    init() {
        coinsInPocket = Bank.send(coins: 100)
    }
    func getCoins(amount: Int) {
        coinsInPocket += Bank.send(coins: amount)
    }
    deinit {
        Bank.receive(coins: coinsInPocket)
    }
}

var v: Person? = Person()
v!.getCoins(amount: 200)
v!.coinsInPocket
Bank.coinsInBank
v = nil
Bank.coinsInBank

// Null Safety
// by default a value cannnot be null
//var w: Int = nil
var x: Int? = nil

// Extensions
// adds functionality to an already existing thing
// converts Double values to meters based on what unit they are
extension Double {
    var km: Double { return self * 1_000.0 }
    var m: Double { return self }
    var cm: Double { return self / 100.0 }
    var mm: Double { return self / 1_000.0 }
    var ft: Double { return self / 3.28084 }
}
let oneInch = 25.4.mm
print("One inch is \(oneInch) meters")

let threeFeet = 3.ft
print("Three feet is \(threeFeet) meters")

// adds functionality to all ints where you can addTwo
extension Int {
    mutating func addTwo() {
        self = self + 2
    }
}

var y = 3
y.addTwo()

// adds functionality to all ints where it will print out "hi" the number the value is
extension Int {
    func repetitions() {
        for _ in 0..<self {
            print("Hi!")
        }
    }
}

6.repetitions()

// Protocols
// are bluprints
protocol Name {
    var name: String { get}
}

//struct Player: Name {}
struct Player: Name {
    var first: String
    var last: String
    
    var name: String {
        return first + " " + last
    }
}

let z = Player(first: "Ajay", last: "Patnaik")
z.name

// Generics
//func swap(_ a: inout Int, _ b: inout Int) {
//    let temp = a
//    a = b
//    b = temp
//}

func genericSwap<T>(_ a: inout T, _ b: inout T) {
    let temp = a
    a = b
    b = temp
}

var aa = 3
var bb = 4
genericSwap(&aa, &bb)
aa
bb

var cc = "Hi"
var dd = "Bye"
genericSwap(&cc, &dd)
cc
dd

// Custom operators
postfix operator +++
extension Int {
    static postfix func +++ (num: inout Int) -> Int {
        num = num + num + num
        return num
    }
}

var ee: Int = 3
let ff = ee+++

// partial function application
// regular add
func radd(a: Int, b: Int) -> Int {
    return a + b;
}
let rsum = radd(a: 3, b: 10)

let normalType = type(of: radd)

func partialAdd(a: Int) -> (Int) -> Int {
    return { b in a + b }
}

let addThree = partialAdd(a: 3)
let partialSum = addThree(10)

let partialType = type(of: partialAdd)

// Higher-order functions
let gg = ["Ajay", "Tom", "Tyler"]
// $0.lowercased could be any function
let lowercase = gg.map { $0.lowercased() }
lowercase

// List Comprehension
let evenSquared = (0..<10).filter({ $0 % 2 == 0 }).map({ $0 * $0 })
evenSquared
