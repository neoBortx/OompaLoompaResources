package com.bortxapps.oompaloomparesources.navigation

import com.bortxapps.oompaloomparesources.navigation.NavigationConstants.OOMPA_LOOMPA_ID

sealed class Screen(
    val routeSourcePath: String,
) {

    abstract fun getFullRoute(): String

    fun getRouteWithId(electionId: String): String {
        return "$routeSourcePath/$electionId"
    }

    //region elections
    object List : Screen("list_screen") {

        override fun getFullRoute(): String {
            return routeSourcePath
        }
    }

    object Detail : Screen("detail_screen") {

        override fun getFullRoute(): String {
            return "$routeSourcePath/{$OOMPA_LOOMPA_ID}"
        }
    }
    //endregion
}
