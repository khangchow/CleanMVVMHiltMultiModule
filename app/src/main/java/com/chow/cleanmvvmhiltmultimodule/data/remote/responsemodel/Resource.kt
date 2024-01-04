package com.chow.cleanmvvmhiltmultimodule.data.remote.responsemodel

sealed class Resource<T> {
    class Success<T>(val data: T? = null) : Resource<T>()
    class Error<T>(val data: T? = null) : Resource<T>()
    data object Loading: Resource<Nothing>()
}