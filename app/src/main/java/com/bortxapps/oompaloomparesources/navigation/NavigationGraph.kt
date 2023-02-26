package com.bortxapps.oompaloomparesources.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bortxapps.oompaloomparesources.navigation.NavigationConstants.OOMPA_LOOMPA_ID
import com.bortxapps.oompaloomparesources.views.MainScreen
import com.bortxapps.oompaloomparesources.views.OompaLoompaDetailScreen

@ExperimentalMaterialApi
@Composable
fun MyAppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.List.getFullRoute())
    {
        composable(route = Screen.List.getFullRoute()) {
            MainScreen {
                navController.navigate(Screen.Detail.getRouteWithId(it.toString()))
            }
        }

        composable(
            route = Screen.Detail.getFullRoute(),
            arguments = listOf(navArgument(OOMPA_LOOMPA_ID) { type = NavType.IntType })
        )
        {
            val id: Int = it.arguments?.getInt(OOMPA_LOOMPA_ID) ?: 0
            OompaLoompaDetailScreen(id,
                onBackNavigation = { navController.navigateUp() })
        }
    }
}