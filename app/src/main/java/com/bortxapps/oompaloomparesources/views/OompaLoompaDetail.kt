package com.bortxapps.oompaloomparesources.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.bortxapps.data.OompaLoompaInfo
import com.bortxapps.oompaloomparesources.R.drawable
import com.bortxapps.oompaloomparesources.R.string
import com.bortxapps.oompaloomparesources.viewmodels.DetailState
import com.bortxapps.oompaloomparesources.viewmodels.DetailState.*
import com.bortxapps.oompaloomparesources.viewmodels.OompaLoompaDetailViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun OompaLoompaDetailScreen(
    id: Int,
    onBackNavigation: () -> Unit,
    viewModel: OompaLoompaDetailViewModel = hiltViewModel()
) {

    viewModel.loadData(id)

    Scaffold(
        topBar = { TopAppBarCustomWithBackButton(onBackNavigation) }
    ) {
        Column(modifier = Modifier.padding(it)) {
            OompaLoompaDetalMainColumn(viewModel.state)
        }
    }
}

@Composable
fun OompaLoompaDetalMainColumn(state: DetailState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        when (state) {
            is DetailReceived -> OompaLoompaDetail(state.item)
            Error -> ShowError()
            Idle -> LoadingSpinner()
            RequestingData -> LoadingSpinner()
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OompaLoompaDetail(oompaLoompaInfo: OompaLoompaInfo) {
    ConstraintLayout(modifier = Modifier.testTag("oompa_detail_layout")) {

        val (image, firstName, lastName, gender, profession, email, age, country, tastes, color, food) = createRefs()

        GlideImage(
            model = oompaLoompaInfo.image,
            contentDescription = "Contact image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .constrainAs(image) {
                    top.linkTo(parent.top, margin = 0.dp)
                    start.linkTo(parent.start, margin = 0.dp)
                    end.linkTo(parent.end, margin = 0.dp)

                }
        )

        Text(
            text = oompaLoompaInfo.firstName,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .testTag("oompa_detail_name")
                .constrainAs(firstName) {
                    top.linkTo(image.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                }
        )

        Text(
            text = oompaLoompaInfo.lastName,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .testTag("oompa_detail_surname")
                .constrainAs(lastName) {
                    top.linkTo(image.bottom, margin = 20.dp)
                    start.linkTo(firstName.end, margin = 5.dp)
                }
        )

        Text(
            text = "(${oompaLoompaInfo.gender})",
            fontSize = 24.sp,
            modifier = Modifier
                .testTag("oompa_detail_gender")
                .constrainAs(gender) {
                    top.linkTo(image.bottom, margin = 20.dp)
                    start.linkTo(lastName.end, margin = 5.dp)
                }
        )

        Text(
            text = oompaLoompaInfo.profession,
            fontSize = 20.sp,
            modifier = Modifier
                .testTag("oompa_detail_profession")
                .constrainAs(profession) {
                    top.linkTo(firstName.bottom, margin = 4.dp)
                    start.linkTo(firstName.start)
                }
        )

        Row(modifier = Modifier
            .constrainAs(email) {
                top.linkTo(profession.bottom, margin = 8.dp)
                start.linkTo(profession.start)
            }) {
            Icon(imageVector = Icons.Default.Email, contentDescription = "")

            Text(
                text = oompaLoompaInfo.email,
                fontSize = 20.sp,
                modifier = Modifier
                    .testTag("oompa_detail_email")
                    .padding(start = 5.dp)
            )
        }

        Row(modifier = Modifier.constrainAs(country) {
            top.linkTo(email.bottom, margin = 4.dp)
            start.linkTo(email.start)
        }) {
            Icon(painter = painterResource(drawable.country), contentDescription = "")

            Text(
                text = oompaLoompaInfo.country,
                fontSize = 20.sp,
                modifier = Modifier
                    .testTag("oompa_detail_country")
                    .padding(start = 5.dp)
            )
        }

        Row(modifier = Modifier.constrainAs(age) {
            top.linkTo(country.bottom, margin = 4.dp)
            start.linkTo(country.start)
        }) {
            Icon(painter = painterResource(drawable.age), contentDescription = "")

            Text(
                text = oompaLoompaInfo.age.toString(),
                fontSize = 20.sp,
                modifier = Modifier
                    .testTag("oompa_detail_age")
                    .padding(start = 5.dp)
            )
        }

        Text(text = stringResource(string.love),
            fontSize = 22.sp,
            modifier = Modifier.constrainAs(tastes) {
                top.linkTo(age.bottom, margin = 10.dp)
                start.linkTo(age.start)
            })

        Row(modifier = Modifier.constrainAs(color) {
            top.linkTo(tastes.bottom, margin = 4.dp)
            start.linkTo(tastes.start)
        }) {
            Icon(painter = painterResource(drawable.color_fav), contentDescription = "")

            Text(
                text = oompaLoompaInfo.tastes.color,
                fontSize = 20.sp,
                modifier = Modifier
                    .testTag("oompa_detail_color")
                    .padding(start = 5.dp)
            )
        }

        Row(modifier = Modifier.constrainAs(food) {
            top.linkTo(color.bottom, margin = 4.dp)
            start.linkTo(parent.start, margin = 20.dp)
        }) {
            Icon(painter = painterResource(drawable.food_fav), contentDescription = "")

            Text(
                text = oompaLoompaInfo.tastes.food,
                fontSize = 20.sp,
                modifier = Modifier
                    .testTag("oompa_detail_food")
                    .padding(start = 5.dp)
            )
        }
    }
}