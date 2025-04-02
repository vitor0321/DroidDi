package com.example.droid_inject_kotlin

import com.example.droid_inject_kotlin.annotations.Inject
import kotlin.reflect.KClass

inline fun <reified T : Any> inject(): T {
    val constructor = T::class.constructors.firstOrNull {
        it.annotations.any { annotation ->
            annotation is Inject
        }
    }?: throw IllegalArgumentException("No constructor with @Inject annotation found for ${T::class.simpleName}")

    val params = constructor.parameters.map { param ->
        val kClass = param.type.classifier as? KClass<*> ?: throw IllegalArgumentException("Invalid parameter type for ${param.type}")

        DroidInjectContainer.resolve(kClass)
    }

    return constructor.call(*params.toTypedArray())
}