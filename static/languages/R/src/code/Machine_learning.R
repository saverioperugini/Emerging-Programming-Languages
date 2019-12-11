#**********************************************************************
#      filename:  Machine_learning.R
#
#
#       Author:  Reichel, Corey J
#       Copyright (c) 2019 Corey Reichel, University of Dayton
#**********************************************************************
library(ggvis)
library(class)
library(gmodels)
library(caret)

#The Data
iris
head(iris)
table(iris$Species) 
summary(iris[c("Petal.Width", "Sepal.Width")])

iris %>% ggvis(~Sepal.Length, ~Sepal.Width, fill = ~Species) %>% layer_points()
cor(iris$Sepal.Length, iris$Sepal.Width) #correlation

iris %>% ggvis(~Petal.Length, ~Petal.Width, fill = ~Species) %>% layer_points()
cor(iris$Petal.Length, iris$Petal.Width)

#Simpsons Paradox

x = levels(iris$Species) #valid entries for Species
x

(iris$Species==x[1])


everyOther = c(TRUE, FALSE)
head(iris[everyOther, 1:4])
head(iris)

cor(iris[iris$Species==x[1],1:4]) #setosa matrix
cor(iris[iris$Species==x[2], 1:4]) #versicolor matrix

# Build your own `normalize()` function
normalize = function(x) {
  numer = x - min(x)
  denom = max(x) - min(x)
  return (numer/denom)
}


iris_norm <- as.data.frame(lapply(iris[1:4], normalize))
iris_norm %>% ggvis(~Sepal.Length, ~Sepal.Width) %>% layer_points()

set.seed(1234)
ind <- sample(2, nrow(iris), replace=TRUE, prob=c(0.67, 0.33))
head(ind)


iris.training <- iris[ind==1, 1:4] #gather training parameters
iris.trainLabels <- iris[ind==1,5] #get training labels

iris.test <- iris[ind==2, 1:4]     #gather test parameters
iris.testLabels <- iris[ind==2, 5] #Get test Answers


iris_pred <- knn(train = iris.training, test = iris.test,
                  cl = iris.trainLabels, k=3)
print(iris_pred) #predicted test results


#irisTestLabels <- data.frame(iris.testLabels)

# Merge `iris_pred` and `iris.testLabels` 
compare <- data.frame(iris_pred, iris.testLabels)
names(compare) <- c("Predicted Species", "Observed Species")
compare


CrossTable(x = iris.testLabels, y = iris_pred, prop.chisq=FALSE)





#Caret
index <- createDataPartition(iris$Species, p=0.75, list=FALSE)

iris.training <- iris[index,]
iris.test <- iris[-index,]

model_knn <- train(iris.training[, 1:4], iris.training[, 5], method='knn')


# Train the model with preprocessing
model_knn <- train(iris.training[, 1:4], iris.training[, 5], method='knn', preProcess=c("center", "scale"))

# Predict values
predictions<-predict.train(object=model_knn,iris.test[,1:4], type="raw")

# Confusion matrix
confusionMatrix(predictions,iris.test[,5])


