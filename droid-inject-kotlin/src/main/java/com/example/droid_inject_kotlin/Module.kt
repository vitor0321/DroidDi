package com.example.droid_inject_kotlin

inline fun <reified T: Any> module(
    isSingleton: Boolean = false,
    noinline provider: Provider<T>,
){
    DroidInjectContainer.register(T::class, provider, isSingleton)
}