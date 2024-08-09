package com.keunsori.domain.repository

import com.keunsori.domain.entity.LoginResult
import com.keunsori.domain.entity.ApiResult
import com.keunsori.domain.entity.UserInfo

interface UserRepository {
    suspend fun tryLogin(googleIdToken: String): ApiResult<LoginResult>

    suspend fun getUserInfo(): UserInfo

    suspend fun refreshAccessToken(): Boolean

    suspend fun getRefreshToken(): String

    suspend fun setRefreshToken(refreshToken: String)

    fun setAccessToken(accessToken: String)

    suspend fun logout()
}