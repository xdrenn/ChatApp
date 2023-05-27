package com.example.chatapp.api.repository

import com.example.chatapp.api.ApiService
import com.example.chatapp.api.request.UserRequest
import com.example.chatapp.api.request.apiRequestFlow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService,
) {
    fun user(userRequest: UserRequest) = apiRequestFlow {
        apiService.putUserData(userRequest)
    }
}