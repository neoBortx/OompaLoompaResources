package com.bortxapps.infrastructure.remote.data

import com.google.gson.annotations.SerializedName

data class OompaLoompaInfoRemote(
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val image: String,
    val profession: String,
    val height: Int,
    val country: String,
    val age: Int,
    val gender: String,
    val email: String,
    @SerializedName("favorite")
    val tastes: TastesRemote
)