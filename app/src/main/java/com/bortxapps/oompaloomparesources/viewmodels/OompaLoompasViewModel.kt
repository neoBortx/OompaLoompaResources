package com.bortxapps.oompaloomparesources.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bortxapps.oompaloomparesources.viewmodels.GenderFilterOptions.*
import com.bortxapps.usercases.GetAllOompaLoompasUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OompaLoompasViewModel @Inject constructor(
    getAllTransactionsUserCase: GetAllOompaLoompasUserCase
) : ViewModel() {

    var filterState by mutableStateOf(FilterState())
        private set

    val oompaLoompas = getAllTransactionsUserCase()

    fun showFilter() {
        filterState = filterState.copy(showFilter = !filterState.showFilter)
    }

    fun setProfessionFilter(profession: String) {
        filterState = filterState.copy(profession = profession)
    }

    fun changeGenderVisibility(genderFilterOptions: GenderFilterOptions) {
        updateGenderFilter(filterState.copy(genderFilterOptions = genderFilterOptions))
    }

    private fun updateGenderFilter(filterState: FilterState) {
        val filter = when (filterState.genderFilterOptions) {
            Male -> "M"
            Female -> "F"
            Both -> ""
        }

        this.filterState = filterState.copy(genderFilter = filter)
    }
}