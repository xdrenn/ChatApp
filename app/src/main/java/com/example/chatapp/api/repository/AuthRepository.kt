package com.example.chatapp.api.repository

import com.example.chatapp.api.ApiService
import com.example.chatapp.api.request.AuthRequest
import com.example.chatapp.api.request.apiRequestFlow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService,
) {
    fun auth(authRequest: AuthRequest) = apiRequestFlow {
        apiService.auth(authRequest)
    }
}