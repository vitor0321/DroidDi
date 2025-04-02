package com.example.droiddibase

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.droid_inject_kotlin.annotations.Inject

class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    init {
        getPosts()
    }

    private fun getPosts() {
        Log.d("MainViewModel", "getPosts")
        repository.fetchData()
    }
}