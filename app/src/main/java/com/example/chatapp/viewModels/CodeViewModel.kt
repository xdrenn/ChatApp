package com.example.chatapp.viewModels

import androidx.lifecycle.MutableLiveData
import com.example.chatapp.api.repository.CodeRepository
import com.example.chatapp.api.request.Code
import com.example.chatapp.api.response.ApiResponse
import com.example.chatapp.api.response.CodeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CodeViewModel @Inject constructor(
    private val codeRepository: CodeRepository,
): BaseViewModel() {

    private val _codeResponse = MutableLiveData<ApiResponse<CodeResponse>>()
    val codeResponse = _codeResponse

    fun code(code: Code, coroutinesErrorHandler: CoroutinesErrorHandler) = baseRequest(
        _codeResponse,
        coroutinesErrorHandler
    ) {
        codeRepository.code(code)
    }
}