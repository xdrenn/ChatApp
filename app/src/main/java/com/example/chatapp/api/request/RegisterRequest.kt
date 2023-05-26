package com.example.chatapp.api.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest (

    @SerializedName("phone")
    val phone: String,

    @SerializedName("username")
    val userName: String,

    @SerializedName("name")
    val name: String

        )