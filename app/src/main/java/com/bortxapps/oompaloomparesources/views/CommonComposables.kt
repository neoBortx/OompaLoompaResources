package com.bortxapps.oompaloomparesources.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bortxapps.oompaloomparesources.R.drawable
import com.bortxapps.oompaloomparesources.R.string

@Composable
fun LoadingSpinner() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("main_screen_loading_spinner"),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ShowError() {
    Column(
        modifier = Modifier
            .testTag("error_component")
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(id = drawable.error),
            contentDescription = "Error",
            Modifier.padding(100.dp, 100.dp, 100.dp, 20.dp),
            tint = Color.White
        )
        Text(text = stringResource(string.error_message), Modifier.padding(20.dp, 0.dp), fontSize = 26.sp, fontWeight = FontWeight.Bold)
    }
}