#**********************************************************************
#      filename:  Homework_Solutions.R
#
#
#       Author:  Reichel, Corey J
#       Copyright (c) 2019 Corey Reichel, University of Dayton
#**********************************************************************

#Question 1
data = mtcars[c(TRUE,FALSE), c("mpg", "hp", "wt", "cyl")]
plot(data)
cor(data) #HP and Cyl have greatest correlation
boxplot(hp~cyl,data=mtcars, main="Car Power Data",
        xlab="Number of Cylinders", ylab="horsepower")


#Qusetion 2
data = mtcars[ ,c("mpg")]
range = (mean(data) + c(-1,1) * 1.96 * sd(data) / sqrt(length(data)))
length(data[data < range[2] & data > range[1]])

