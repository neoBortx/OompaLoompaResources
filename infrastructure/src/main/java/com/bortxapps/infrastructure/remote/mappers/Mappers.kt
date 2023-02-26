package com.bortxapps.infrastructure.remote.mappers

import com.bortxapps.data.OompaLoompaInfo
import com.bortxapps.data.OompaLoompaInfoListWrapper
import com.bortxapps.data.Tastes
import com.bortxapps.infrastructure.remote.data.OompaLoompaInfoListRemote
import com.bortxapps.infrastructure.remote.data.OompaLoompaInfoRemote
import com.bortxapps.infrastructure.remote.data.TastesRemote

fun OompaLoompaInfoListRemote.toDomain() =
    OompaLoompaInfoListWrapper(current, total, results.toDomain())

fun List<OompaLoompaInfoRemote>.toDomain() = map { it.toDomain() }

fun OompaLoompaInfoRemote.toDomain() =
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
        tastes.toDomain()
    )

fun TastesRemote.toDomain() = Tastes(color, food, randomString, song)