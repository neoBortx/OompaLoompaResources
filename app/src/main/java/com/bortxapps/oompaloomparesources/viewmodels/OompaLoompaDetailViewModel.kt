package com.bortxapps.oompaloomparesources.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bortxapps.usercases.GetOompaLoompaDetailUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OompaLoompaDetailViewModel @Inject constructor(
    private val getOompaLoompaDetailUserCase: GetOompaLoompaDetailUserCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    var state by mutableStateOf<DetailState>(DetailState.Idle)
        private set

    fun loadData(id: Int) {
        state = DetailState.RequestingData
        viewModelScope.launch {
            withContext(ioDispatcher) {
                getOompaLoompaDetailUserCase(id).collect {
                    state = it?.let {
                        DetailState.DetailReceived(it)
                    } ?: DetailState.Error
                }
            }
        }
    }
}