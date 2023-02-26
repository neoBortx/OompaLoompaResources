package com.bortxapps.oompaloomparesources.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bortxapps.data.OompaLoompaInfo
import com.bortxapps.data.Tastes
import com.bortxapps.oompaloomparesources.ui.theme.Grey200
import com.bortxapps.oompaloomparesources.ui.theme.OompaLoompaResourcesTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OompaLoompaCard(oompaLoompaInfo: OompaLoompaInfo, navigateToDetail: (Int) -> Unit) {
    Card(
        backgroundColor = Grey200,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { navigateToDetail(oompaLoompaInfo.id) }
            .testTag("main_screen_ommpa_card")
    ) {
        ConstraintLayout {

            val (image, firstName, lastName, gender, profession, email) = createRefs()

            GlideImage(
                model = oompaLoompaInfo.image,
                contentDescription = "Contact image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(90.dp)
                    .width(90.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .constrainAs(image) {
                        top.linkTo(parent.top, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                        start.linkTo(parent.start, margin = 10.dp)

                    }
            )

            Text(
                text = oompaLoompaInfo.firstName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .testTag("ommpa_card_name")
                    .constrainAs(firstName) {
                        top.linkTo(parent.top, margin = 10.dp)
                        start.linkTo(image.end, margin = 20.dp)
                    }
            )

            Text(
                text = oompaLoompaInfo.lastName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .testTag("ommpa_card_lastname")
                    .constrainAs(lastName) {
                        top.linkTo(parent.top, margin = 10.dp)
                        start.linkTo(firstName.end, margin = 5.dp)
                    }
            )

            Text(
                text = "(${oompaLoompaInfo.gender})",
                fontSize = 20.sp,
                modifier = Modifier.constrainAs(gender) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(lastName.end, margin = 5.dp)
                }
            )

            Text(
                text = oompaLoompaInfo.profession,
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(profession) {
                    top.linkTo(firstName.bottom, margin = 4.dp)
                    start.linkTo(firstName.start)
                }
            )

            Text(
                text = oompaLoompaInfo.email,
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(email) {
                    top.linkTo(profession.bottom)
                    start.linkTo(profession.start)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OompaLoompaResourcesTheme {
        OompaLoompaCard(
            OompaLoompaInfo(
                id = 10,
                lastName = "",
                country = "",
                email = "",
                age = 7,
                firstName = "",
                gender = "",
                height = 95,
                image = "",
                profession = "",
                tastes = Tastes(color = "", food = "", randomString = "", song = "")
            )
        ) {}
    }
}