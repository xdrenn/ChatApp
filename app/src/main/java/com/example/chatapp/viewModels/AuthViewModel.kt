package com.example.chatapp.viewModels

import androidx.lifecycle.MutableLiveData
import com.example.chatapp.api.repository.AuthRepository
import com.example.chatapp.api.request.AuthRequest
import com.example.chatapp.api.response.ApiResponse
import com.example.chatapp.api.response.AuthResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
): BaseViewModel() {

    private val _authResponse = MutableLiveData<ApiResponse<AuthResponse>>()

    fun auth(authRequest: AuthRequest, coroutinesErrorHandler: CoroutinesErrorHandler) = baseRequest(
        _authResponse,
        coroutinesErrorHandler
    ) {
        authRepository.auth(authRequest)
    }
}