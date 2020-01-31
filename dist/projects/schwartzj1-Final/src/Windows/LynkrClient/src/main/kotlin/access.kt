//*******************************************************************************
//
//      filename:  access.kt
//
//   description:  Access control module
//
//        author:  Schwartz, Jacob T.
//       Copyright (c) 2019 Schwartz, Jacob T.
//
//******************************************************************************

object Access {
    fun logout() {
        Executioner.run("shutdown /l")
    }

    fun lock() {
        Executioner.run("rundll32.exe user32.dll,LockWorkStation")
    }
}