package com.bortxapps.oompaloomparesources

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import com.bortxapps.oompaloomparesources.navigation.MyAppNavHost
import com.bortxapps.oompaloomparesources.ui.theme.OompaLoompaResourcesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OompaLoompaResourcesTheme {
                MyAppNavHost()
            }
        }
    }
}

