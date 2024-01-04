package com.chow.cleanmvvmhiltmultimodule.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
        val token = ""
        newRequest.addHeader("Authorization", "Bearer $token")
        return chain.proceed(newRequest.build())
    }
}