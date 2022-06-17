{----------------------------------------------------------------------------------
/
/      filename:  Syntax.idr
/
/   description:  Provides some of the basic syntax Idris uses. Shows examples of
/					most of the unique features of the languages (aside from what is
/					shown in the other files). 
/
/        author:  Henry, Jonathon P.
/      Copyright (c) 2019 Jonathon P. Henry, University of Dayton
----------------------------------------------------------------------------------}


--Basic function identifications
add : Nat -> Nat -> Nat
add Z x = x
add (S k) x = S (add k x)

mul : Nat -> Nat -> Nat
mul Z y = Z
mul (S k) y = add y (mul k y)

fibo : Nat -> Nat
fibo Z = 1
fibo (S Z) = 1
fibo (S (S n)) = fibo (S n) + fibo (n)

--Functions, data constructors and type constructs can be given infix operators
(++) : Nat -> Nat -> Nat
(++) x y = 2 * (x + y)

--Anonymous functions
myapply : (Nat -> Nat) -> Nat -> Nat
myapply f x = f x

applySquare : Nat -> Nat
applySquare x = myapply (\a => a * a) x

--Null typing
-- Default definition for the Maybe type : data Maybe a = Nothing | Just a

first : List a -> Maybe a
first [] = Nothing
first (x :: xs) = Just x

--Where clauses
reverse : List a -> List a
reverse xs = revAcc [] xs where
  revAcc : List a -> List a -> List a
  revAcc acc [] = acc
  revAcc acc (x :: xs) = revAcc (x :: acc) xs


--currying
myadd : (Int, Int) -> Int
myadd (x, y) = x + y

myaddseven : Int -> Int
myaddseven = curry myadd 7

--Another apporach
myaddeight : Int -> Int
myaddeight = (+) 8

--Dependent type example
isSingleton : Bool -> Type
isSingleton True = Nat
isSingleton False = List Nat

sum : (single : Bool) -> isSingleton single -> Nat
sum True x = x
sum False [] = 0
sum False (x :: xs) = x + sum False xs


--Lazy evaluation
ifThenElse : Bool -> Lazy a -> Lazy a -> a
ifThenElse True t f = t
ifThenElse False t f = f

--Stream is an infinite data type that uses Lazy evaluation
ones : Stream Nat
ones = 1 :: ones


--Records
record Student where
    constructor MkStudent
    firstName, lastName, language : String
    age : Int


John : Student
John = MkStudent "John" "Doe" "Idris" 21

    --accessing record data:
    --firstName John
    --age John
    --etc

--List comprehensions

--Syntax : [expression | qualifiers ]

pythag_trips : Int -> List (Int, Int, Int)
pythag_trips n = [(x, y, z) | z <- [1..n], y <- [1..z], x <- [1..y],
                                          x*x + y*y == z*z ]


--Dependent Pairs

data myDPair : (a : Type) -> (P : a -> Type) -> Type where
   MkMyDPair : {P : a -> Type} -> (x : a) -> P x -> myDPair a P


--Totality

--Keyword total or partial

total
fibo2 : Nat -> Nat
fibo2 Z = 1
fibo2 (S Z) = 1
fibo2 (S (S n)) = fibo2 (S n) + fibo2 (n)
