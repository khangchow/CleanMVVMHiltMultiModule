package com.chow.cleanmvvmhiltmultimodule.data.remote.responsemodel

data class BaseResponse<T>(
    val data: T? = null,
    val error: String? = null
)