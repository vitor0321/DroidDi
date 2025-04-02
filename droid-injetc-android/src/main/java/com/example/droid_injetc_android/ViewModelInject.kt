package com.example.droid_injetc_android

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.droid_inject_kotlin.DroidInjectContainer
import kotlin.reflect.KClass

@Composable
inline fun <reified VM: Any> droidInjectViewModel(): VM {
    return viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val kClass = VM::class
                val viewModelInstance = DroidInjectContainer.resolve(kClass as KClass<out Any>)
                return modelClass.cast(viewModelInstance) ?:
                    throw IllegalArgumentException("Cannot cast ViewModel ${viewModelInstance::class.simpleName} to ${modelClass.simpleName}")
            }
        }
    )
}
