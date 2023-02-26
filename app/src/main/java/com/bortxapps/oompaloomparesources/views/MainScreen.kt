package com.bortxapps.oompaloomparesources.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.bortxapps.data.OompaLoompaInfo
import com.bortxapps.oompaloomparesources.viewmodels.FilterState
import com.bortxapps.oompaloomparesources.viewmodels.OompaLoompasViewModel

@Composable
fun MainScreen(viewModel: OompaLoompasViewModel = hiltViewModel(), onNavigateToDetail: (Int) -> Unit) {

    val list: LazyPagingItems<OompaLoompaInfo> = viewModel.oompaLoompas.collectAsLazyPagingItems()

    Scaffold(
        topBar = { TopAppBarCustom(viewModel::showFilter) }
    ) {
        Column(modifier = Modifier.padding(it)) {
            FiltersControl(
                viewModel.filterState,
                viewModel::setProfessionFilter,
                viewModel::changeGenderVisibility
            )
            MainWindow(list, onNavigateToDetail, viewModel.filterState)
        }
    }
}

@Composable
fun MainWindow(
    list: LazyPagingItems<OompaLoompaInfo>,
    onNavigateToDetail: (Int) -> Unit,
    filterState: FilterState
) {
    LazyColumn(
        modifier = Modifier
            .width(400.dp)
            .testTag("main_screen_oompa_table")
    ) {
        items(list, key = { it.id })
        {
            it?.let {
                if (it.profession.lowercase().contains(filterState.profession.lowercase())
                    && it.gender.contains(filterState.genderFilter)
                ) {
                    OompaLoompaCard(it, onNavigateToDetail)
                }
            }
        }

        when (list.loadState.refresh) { //FIRST LOAD
            is LoadState.Error -> {
                item { ShowError() }
            }
            is LoadState.Loading -> {
                item { LoadingSpinner() }
            }
            else -> {
                //DO nothing
            }
        }

        when (list.loadState.append) { // Pagination
            is LoadState.Error -> {
                item { ShowError() }
            }
            is LoadState.Loading -> {
                item { LoadingSpinner() }
            }
            else -> {
                //DO nothing
            }
        }
    }
}
