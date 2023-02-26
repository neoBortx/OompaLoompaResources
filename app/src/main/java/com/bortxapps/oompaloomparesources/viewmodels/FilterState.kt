package com.bortxapps.oompaloomparesources.viewmodels

import com.bortxapps.oompaloomparesources.viewmodels.GenderFilterOptions.Both

data class FilterState(
    val showFilter: Boolean = false,
    val profession: String = "",
    val genderFilterOptions: GenderFilterOptions = Both,
    val genderFilter: String = ""
)
