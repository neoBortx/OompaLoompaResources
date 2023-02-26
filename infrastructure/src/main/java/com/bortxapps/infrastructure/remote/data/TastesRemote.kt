package com.bortxapps.infrastructure.remote.data

import com.google.gson.annotations.SerializedName

data class TastesRemote(
    val color: String,
    val food: String,
    @SerializedName("random_string")
    val randomString: String,
    val song: String,
)