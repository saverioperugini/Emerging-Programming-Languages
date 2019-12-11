#**********************************************************************
#      filename:  Basics.R
#
#
#       Author:  Reichel, Corey J
#       Copyright (c) 2019 Corey Reichel, University of Dayton
#**********************************************************************



# '=' vs. '<-' vs. '->' vs. '<<-' a digression
x  <- FALSE

vec = c(1,2,3)

vec1 = c(TRUE,1,"sned")
typeof(vec1)

1:50 

seq(2, 100, by=2)
head(iris)

l = list("a"= c("sneed", "thing"), "b"= 1:50, "c"="1" )



#Array Syntax
arr = array(data=4:100, dim=c(2, 5, 3))
print(arr)

ncol(arr)
nrow(arr)
dim(arr)[3]

arr[row, col, mat]
print(arr[1,])

vect = c(51,52,53)
length(vect)
vect
names(vect)
names(vect) = c("A", "B","C")
vect
vect["C"]


#data frames: list of list of equal length
a = c(1,TRUE,"4")
class(a)
b = c(1,2,3)
class(b)
c = c(1,2,3)
d = c(1,2,3)
framed = data.frame(a,b,c,d)

typeof(framed$a[2])


x <- data.frame("SN" = 2:3, "Age" = c(21,15), "Name" = c("John", "Dora"), stringsAsFactors = FALSE)
x
x$SN
x$Age
x[2, ]
x[2,2:3]

framed
row.names(framed)
row.names(framed) = c("first", "Second", "Third")
names(framed)
print(framed)
print(framed[2,2])

#Graphs
attach(mtcars)
print(mtcars)
head(mtcars)
plot(mtcars)
plot(wt,mpg)
hist(hp)

#Classes
boxplot(mpg~cyl,data=mtcars, main="Car Milage Data",
        xlab="Number of Cylinders", ylab="Miles Per Gallon")

#Creating classes
student <- setRefClass("student",
  fields = list(name = "character", age = "numeric", GPA = "numeric"),
  methods = list(
    birthday = function(){
      age <<- age + 1
    }
  )
)

john = student(name="John", age=19, GPA=2.5)
john$birthday()
print(john)


box =  setRefClass("box", fields = list(value="numeric", nextitem="box"))
#note: objects are pass by value
#Reference objects are pass by reference

#Concepts
factorial = function(n){
  if (n == 1){
    return(1)
  }else 
    return(n * factorial(n-1))
}
factfunc = function(n){
  function(){
    if (n == 1){
      return(1)
    }else 
      return(n * factorial(n-1))
  }} #First Class Functions

confidenceInterval = function(n, z){
  function(mean, sd) {  # 95% = 1.960, 99% = 2.576
    return(mean + c(-1,1) * z * SD / sqrt(n))
  }
} #application

compare = function(func, x, y){
  return(func(x, y))
} # HOFs
min = function(x, y){
  if (x < y){
    return(x)
  }else{
    return(y)
  }
}
compare(min, 1, 4)        

(function(variable) print(100 - variable))(1) #anonymous functions


closure = function(x) {
  x = x
  function() {
    #x = x + 1
    x <<- x + 1
    return(x)
  }
}

env1 = closure(0)
env2 = closure(100)
env1()
env1()
env2()

lazy = function(x,y){
  print(x)
}

print(undefinedVariable)
lazy(4, undefinedVariable)

z = call("factorial",(5))   #Thunk
eval(z)                     #Thaw

infList = function(x){
  thunk = call("factorial", (x))
  function(){
    print(eval(thunk))
    x <<- x+1
    thunk <<- call("factorial", (x))
  }
}

env  = infList(1)
env()
env()
env()

#Continuation passing style
NonCPS = function(n){
  if(n == 0){
    return(1)
  }else{
    return(n*factorial(n-1))
  }
}
NonCPS(5)

factorialCPS = function(n, acc){
  if(n == 1){
    acc(n)
  }else{
    factorialCPS(n-1, (function(x) acc(n * x)))
  }
}

factorialCPS(5, (function(x) print(x)))

#TRAMPOLINES

#Currying (imported from functional package)
library(functional)
cur = function(x,y,z){
  print(x)
  print(y)
  print(z)
}
a = Curry(cur, 1)
b = Curry(a, 2)
c = Curry(b, 3)
c()

quantile(seq(1, 9, by =3), probs=c(99.99,20)/100)
rnorm()