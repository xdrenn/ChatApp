package com.example.chatapp.api.request

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("phone")
    val phone: String
)
