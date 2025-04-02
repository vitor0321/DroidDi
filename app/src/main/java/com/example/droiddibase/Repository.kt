package com.example.droiddibase

import com.example.droid_inject_kotlin.annotations.Inject

class Repository @Inject constructor(
    private val logger: Logger
) {
    fun fetchData() {
        logger.log("Fetching data...")
    }
}