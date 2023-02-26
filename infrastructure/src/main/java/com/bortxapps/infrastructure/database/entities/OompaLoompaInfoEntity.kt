package com.bortxapps.infrastructure.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "oompaloompas")
data class OompaLoompaInfoEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val lastName: String,
    val image: String,
    val profession: String,
    val height: Int,
    val firstName: String,
    val country: String,
    val age: Int,
    val gender: String,
    val email: String,
    val color: String,
    val food: String,
    val randomString: String,
    val song: String,
)