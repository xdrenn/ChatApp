package com.example.chatapp.api.repository

import com.example.chatapp.api.ApiService
import com.example.chatapp.api.request.RegisterRequest
import com.example.chatapp.api.request.apiRequestFlow
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val apiService: ApiService,
) {
    fun register(registerRequest: RegisterRequest) = apiRequestFlow {
        apiService.registration(registerRequest)
    }
}