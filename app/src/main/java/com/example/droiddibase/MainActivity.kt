package com.example.droiddibase

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.droid_inject_kotlin.DroidInjectContainer
import com.example.droid_inject_kotlin.inject
import com.example.droid_inject_kotlin.module
import com.example.droid_injetc_android.droidInjectViewModel
import com.example.droiddibase.ui.theme.DroidDIBaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        module<Logger>(isSingleton = true) {
            ConsoleLogger()
        }

        module<Repository> {
            inject<Repository>()
        }

        module<MainViewModel> {
            inject<MainViewModel>()
        }

        val repository = DroidInjectContainer.resolve(Repository::class)
        repository.fetchData()

        val repository2 = DroidInjectContainer.resolve(Repository::class)
        repository2.fetchData()


        enableEdgeToEdge()
        setContent {
            DroidDIBaseTheme {
                val mainViewModel = droidInjectViewModel<MainViewModel>()
                Log.d("LOG MainActivity", "MainViewModel: $mainViewModel")

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DroidDIBaseTheme {
        Greeting("Android")
    }
}