package com.bortxapps.data

data class OompaLoompaInfoListWrapper(
    val currentPage: Int,
    val totalPages: Int,
    val oompaLoompas: List<OompaLoompaInfo>
)