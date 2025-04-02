package com.example.droid_inject_kotlin

import kotlin.reflect.KClass

object DroidInjectContainer {

    private val providers = mutableMapOf<KClass<*>, Provider<*>>()
    private val singletons = mutableMapOf<KClass<*>, Any>()

    fun <T : Any> register(kClazz: KClass<T>, provider: Provider<T>, isSingleton: Boolean = false) {
        if (isSingleton) {
            providers[kClazz] = {
                singletons.getOrPut(kClazz) {
                    provider()
                }
            }
        } else {
            providers[kClazz] = provider
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> resolve(kClazz: KClass<T>): T {
        val provider = providers[kClazz]
            ?: throw IllegalArgumentException("No provider found for ${kClazz.simpleName}")
        return provider() as T
    }
}