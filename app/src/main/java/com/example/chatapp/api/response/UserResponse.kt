package com.example.chatapp.api.response

import com.google.gson.annotations.SerializedName

data class UserResponse (

        @SerializedName("name")
        val name: String,

        @SerializedName("username")
        val userName: String,

        @SerializedName("birthday")
        var birthday: String,

        @SerializedName("city")
        val city: String,

        @SerializedName("phone")
        val phone: String,

        @SerializedName("avatar")
        val avatar: String,

        )