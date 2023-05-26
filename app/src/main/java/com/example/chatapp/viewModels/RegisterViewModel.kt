package com.example.chatapp.viewModels

import androidx.lifecycle.MutableLiveData
import com.example.chatapp.api.repository.RegisterRepository
import com.example.chatapp.api.request.RegisterRequest
import com.example.chatapp.api.response.ApiResponse
import com.example.chatapp.api.response.AuthResponse
import com.example.chatapp.api.response.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepository: RegisterRepository,
): BaseViewModel() {

    private val _registerResponse = MutableLiveData<ApiResponse<RegisterResponse>>()
    val registerResponse = _registerResponse

    fun register(registerRequest: RegisterRequest, coroutinesErrorHandler: CoroutinesErrorHandler) = baseRequest(
        _registerResponse,
        coroutinesErrorHandler
    ) {
        registerRepository.register(registerRequest)
    }
}