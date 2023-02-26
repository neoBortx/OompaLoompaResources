package com.bortxapps.infrastructure.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key")
data class RemoteKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,
    val currentPage: Int,
    val totalPages: Int
)
