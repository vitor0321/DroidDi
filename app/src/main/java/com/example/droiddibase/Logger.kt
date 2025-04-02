package com.example.droiddibase

interface Logger {
    fun log(msg: String)
}

class ConsoleLogger : Logger {

    override fun log(msg: String) {
        println("LOG $this: $msg")
    }
}