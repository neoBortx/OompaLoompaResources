package com.bortxapps.infrastructure.remote.data

data class OompaLoompaInfoListRemote(
    val current: Int,
    val total: Int,
    val results: List<OompaLoompaInfoRemote>
)