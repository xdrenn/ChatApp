package com.example.chatapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.api.token.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TokenViewModel @Inject constructor(
    private val sessionManager: SessionManager,
): ViewModel() {

    val token = MutableLiveData<String?>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            sessionManager.getToken().collect {
                withContext(Dispatchers.Main) {
                    token.value = it
                }
            }
        }
    }

    fun saveToken(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
           sessionManager.saveToken(token)
        }
    }

    fun deleteToken() {
        viewModelScope.launch(Dispatchers.IO) {
            sessionManager.deleteToken()
        }
    }
}