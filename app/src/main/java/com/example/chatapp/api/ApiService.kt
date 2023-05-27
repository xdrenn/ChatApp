package com.example.chatapp.api

import com.example.chatapp.api.request.AuthRequest
import com.example.chatapp.api.request.CodeRequest
import com.example.chatapp.api.request.RegisterRequest
import com.example.chatapp.api.request.UserRequest
import com.example.chatapp.api.response.AuthResponse
import com.example.chatapp.api.response.CodeResponse
import com.example.chatapp.api.response.UserResponse
import com.example.chatapp.api.response.RefreshTokenResponse
import com.example.chatapp.api.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("api/v1/users/send-auth-code/")
    suspend fun auth(
        @Body authRequest: AuthRequest
    ): Response<AuthResponse>

    @Headers("Content-Type: application/json")
    @POST("api/v1/users/check-auth-code/")
    suspend fun code(
        @Body codeRequest: CodeRequest
    ): Response<CodeResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/v1/users/register/")
    suspend fun registration(
        @Body registerRequest: RegisterRequest
    ): Response<RegisterResponse>

    @Headers("Content-Type: application/json")
    @PUT("/api/v1/users/me/")
    suspend fun putUserData(
        @Body userRequest: UserRequest
    ): Response<UserResponse>




    @Headers("Content-Type: application/json")
    @POST("api/v1/users/refresh-token/")
    suspend fun refreshToken(
        @Header("Authorization") token: String
    ): Response<RefreshTokenResponse>
}