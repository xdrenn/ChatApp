package com.example.chatapp.api

import com.example.chatapp.api.request.Auth
import com.example.chatapp.api.request.Code
import com.example.chatapp.api.response.AuthResponse
import com.example.chatapp.api.response.CodeResponse
import com.example.chatapp.api.response.RefreshTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("api/v1/users/send-auth-code/")
    suspend fun auth(
        @Body auth: Auth
    ): Response<AuthResponse>

    @Headers("Content-Type: application/json")
    @POST("api/v1/users/check-auth-code/")
    suspend fun code(
        @Body code: Code
    ): Response<CodeResponse>

    @Headers("Content-Type: application/json")
    @POST("api/v1/users/refresh-token/")
    suspend fun refreshToken(
        @Header("Authorization") token: String
    ): Response<RefreshTokenResponse>
}