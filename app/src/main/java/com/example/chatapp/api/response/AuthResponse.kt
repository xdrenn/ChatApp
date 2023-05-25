package com.example.chatapp.api.response

import com.google.gson.annotations.SerializedName

data class AuthResponse (
    @SerializedName("is_success")
    val isSuccess: Boolean = true
        )