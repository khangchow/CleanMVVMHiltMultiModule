package com.chow.cleanmvvmhiltmultimodule.data.mapper

import com.chow.cleanmvvmhiltmultimodule.data.local.entity.UserEntity
import com.chow.cleanmvvmhiltmultimodule.data.remote.responsemodel.UserResponse
import com.chow.cleanmvvmhiltmultimodule.domain.model.User
import com.chow.cleanmvvmhiltmultimodule.extension.orZero

fun UserResponse?.toUser() = User(
    id = this?.id.orZero(),
    username = this?.username ?: "",
    email = this?.email ?: ""
)

fun UserEntity.toUser() = User(
    id = this.id,
    username = this.username,
    email = this.email
)

fun User.toUserEntity() = UserEntity(
    id = this.id,
    username = this.username,
    email = this.email
)
