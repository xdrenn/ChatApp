package com.example.chatapp.api.request

import com.google.gson.annotations.SerializedName

data class Code(
    @SerializedName("phone")
    val phone: String,

    @SerializedName("code")
    val code: String
)
