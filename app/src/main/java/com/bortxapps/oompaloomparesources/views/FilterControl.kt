package com.bortxapps.oompaloomparesources.views

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bortxapps.oompaloomparesources.R.string
import com.bortxapps.oompaloomparesources.ui.theme.Grey500
import com.bortxapps.oompaloomparesources.viewmodels.FilterState
import com.bortxapps.oompaloomparesources.viewmodels.GenderFilterOptions

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FiltersControl(
    filterState: FilterState,
    setProfessionFilter: (String) -> Unit,
    changeGenderVisibility: (GenderFilterOptions) -> Unit,
) {
    AnimatedVisibility(
        visible = filterState.showFilter,
        enter = slideInVertically() + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut() + scaleOut(targetScale = 1.2f)
    ) {

        ConstraintLayout(
            modifier = Modifier
                .background(color = Grey500)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            val (profession, professionEdit, male, maleEdit, female, femaleEdit, both, bothEdit) = createRefs()

            Text(text = stringResource(string.profession),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(profession) {
                    top.linkTo(parent.top, margin = 18.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                })
            TextField(
                value = filterState.profession,
                onValueChange = setProfessionFilter,
                modifier = Modifier
                    .testTag("filter_profession_te")
                    .constrainAs(professionEdit) {
                        top.linkTo(parent.top, margin = 5.dp)
                        start.linkTo(profession.end, margin = 10.dp)
                        end.linkTo(parent.end, margin = 10.dp)
                    }
            )

            Text(text = stringResource(string.male),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(male) {
                    top.linkTo(professionEdit.bottom, margin = 15.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                })
            RadioButton(
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.White,
                    unselectedColor = Color.White,
                    disabledColor = Color.White,
                ),
                selected = filterState.genderFilterOptions == GenderFilterOptions.Male,
                onClick = { changeGenderVisibility(GenderFilterOptions.Male) },
                modifier = Modifier
                    .testTag("filter_male_rb")
                    .constrainAs(maleEdit) {
                        top.linkTo(professionEdit.bottom, margin = 5.dp)
                        start.linkTo(male.end, margin = 5.dp)
                    }
            )
            Text(text = stringResource(string.female),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(female) {
                        top.linkTo(professionEdit.bottom, margin = 15.dp)
                        start.linkTo(maleEdit.end, margin = 10.dp)
                    })
            RadioButton(
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.White,
                    unselectedColor = Color.White,
                    disabledColor = Color.White,
                ),
                selected = filterState.genderFilterOptions == GenderFilterOptions.Female,
                onClick = { changeGenderVisibility(GenderFilterOptions.Female) },
                modifier = Modifier
                    .testTag("filter_female_rb")
                    .constrainAs(femaleEdit) {
                        top.linkTo(professionEdit.bottom, margin = 5.dp)
                        start.linkTo(female.end, margin = 5.dp)
                    }
            )
            Text(text = stringResource(string.both),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(both) {
                    top.linkTo(professionEdit.bottom, margin = 15.dp)
                    start.linkTo(femaleEdit.end, margin = 10.dp)
                })
            RadioButton(
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.White,
                    unselectedColor = Color.White,
                    disabledColor = Color.White,
                ),
                selected = filterState.genderFilterOptions == GenderFilterOptions.Both,
                onClick = { changeGenderVisibility(GenderFilterOptions.Both) },
                modifier = Modifier
                    .testTag("filter_both_rb")
                    .constrainAs(bothEdit) {
                        top.linkTo(professionEdit.bottom, margin = 5.dp)
                        start.linkTo(both.end, margin = 5.dp)
                    }
            )
        }
    }
}