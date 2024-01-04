package com.chow.cleanmvvmhiltmultimodule.data.remote.api

import com.chow.cleanmvvmhiltmultimodule.data.remote.responsemodel.BaseResponse
import com.chow.cleanmvvmhiltmultimodule.data.remote.responsemodel.UserResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiService {
    @GET
    fun getUser(): Flow<BaseResponse<UserResponse>>
}