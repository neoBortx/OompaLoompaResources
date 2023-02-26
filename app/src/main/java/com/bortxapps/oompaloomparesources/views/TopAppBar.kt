package com.bortxapps.oompaloomparesources.views

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bortxapps.oompaloomparesources.R
import com.bortxapps.oompaloomparesources.ui.theme.Grey500

@Composable
fun TopAppBarCustom(actionCallBack: () -> Unit) {
    TopAppBar(
        backgroundColor = Grey500,
        title = { Text(stringResource(id = R.string.app_name)) },
        actions = {
            IconButton(
                modifier = Modifier.testTag("filter_menu_button"),
                onClick = actionCallBack
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        elevation = 0.dp
    )
}

@Composable
fun TopAppBarCustomWithBackButton(onBackNavigation: () -> Unit) {
    TopAppBar(
        backgroundColor = Grey500,
        title = { Text(stringResource(id = R.string.app_name)) },
        navigationIcon = {
            IconButton(onClick = {
                onBackNavigation()
            }) {
                Icon(Icons.Default.ArrowBack, "", tint = Color.White)
            }
        },
        elevation = 0.dp
    )
}


