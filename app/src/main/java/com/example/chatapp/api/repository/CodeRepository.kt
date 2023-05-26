package com.example.chatapp.api.repository

import com.example.chatapp.api.ApiService
import com.example.chatapp.api.request.CodeRequest
import com.example.chatapp.api.request.apiRequestFlow
import javax.inject.Inject

class CodeRepository @Inject constructor(
    private val apiService: ApiService,
) {
    fun code(codeRequest: CodeRequest) = apiRequestFlow {
        apiService.code(codeRequest)
    }
}