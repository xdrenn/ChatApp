package com.example.chatapp.api.request

import com.google.gson.annotations.SerializedName

data class UserRequest (

    @SerializedName("name")
    val name: String,

    @SerializedName("username")
    val userName: String,

    @SerializedName("birthday")
    val birthday: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("phone")
    val phone: String,

    @SerializedName("avatar")
    val avatar: String,

    )