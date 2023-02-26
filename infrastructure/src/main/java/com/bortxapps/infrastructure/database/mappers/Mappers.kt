package com.bortxapps.infrastructure.database.mappers

import com.bortxapps.data.OompaLoompaInfo
import com.bortxapps.data.Tastes
import com.bortxapps.infrastructure.database.entities.OompaLoompaInfoEntity

fun List<OompaLoompaInfo>.toEntity() = map { it.toEntity() }

fun OompaLoompaInfo.toEntity() =
    OompaLoompaInfoEntity(
        id,
        lastName,
        image,
        profession,
        height,
        firstName,
        country,
        age,
        gender,
        email,
        tastes.color,
        tastes.food,
        tastes.randomString,
        tastes.song
    )

fun OompaLoompaInfoEntity.toDomain() =
    OompaLoompaInfo(
        id,
        lastName,
        image,
        profession,
        height,
        firstName,
        country,
        age,
        gender,
        email,
        Tastes(color, food, randomString, song)
    )