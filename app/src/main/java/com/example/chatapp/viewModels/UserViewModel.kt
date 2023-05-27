package com.example.chatapp.viewModels

import androidx.lifecycle.MutableLiveData
import com.example.chatapp.api.repository.UserRepository
import com.example.chatapp.api.request.UserRequest
import com.example.chatapp.api.response.ApiResponse
import com.example.chatapp.api.response.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
): BaseViewModel() {

    private val _userResponse = MutableLiveData<ApiResponse<UserResponse>>()
    val userResponse = _userResponse

    fun user(userRequest: UserRequest, coroutinesErrorHandler: CoroutinesErrorHandler) = baseRequest(
        _userResponse,
        coroutinesErrorHandler
    ) {
        userRepository.user(userRequest)
    }
}