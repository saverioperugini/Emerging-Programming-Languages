//*******************************************************************************
//
//      filename:  Power.kt
//
//   description:  Power control module
//
//        author:  Schwartz, Jacob T.
//       Copyright (c) 2019 Schwartz, Jacob T. University of Dayton
//
//******************************************************************************

object Power {
    fun shutdown() {
        Executioner.run("shutdown /s")
    }

    fun restart() {
        Executioner.run("shutdown /r")
    }

    fun hibernate() {
        Executioner.run("shutdown /h")
    }
}