package com.bortxapps.oompaloomparesources.viewmodels

import com.bortxapps.data.OompaLoompaInfo

sealed class DetailState {
    object Idle : DetailState()
    object Error : DetailState()
    object RequestingData : DetailState()
    class DetailReceived(val item: OompaLoompaInfo) : DetailState()
}


