package com.bortxapps.oompaloomparesources.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bortxapps.data.OompaLoompaInfo
import com.bortxapps.data.Tastes
import com.bortxapps.oompaloomparesources.MainActivity
import com.bortxapps.usercases.GetAllOompaLoompasUserCase
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.*
import io.mockk.impl.annotations.*
import kotlinx.coroutines.flow.flow
import org.junit.*
import org.junit.runner.*

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainScreenEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @RelaxedMockK
    @BindValue
    lateinit var getAllOompaLoompasUserCase: GetAllOompaLoompasUserCase

    @OptIn(ExperimentalPagingApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { getAllOompaLoompasUserCase.invoke() } returns flow {
            emit(PagingData.from(generateOompaLoompasList()))
        }
    }

    @Test
    fun test_filter_by_male_gender() {
        composeTestRule.onNodeWithTag("main_screen_oompa_table").assertIsDisplayed()
        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("main_screen_ommpa_card")
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("filter_menu_button").performClick()
        composeTestRule.onNodeWithTag("filter_male_rb").performClick()

        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("main_screen_ommpa_card")
                .fetchSemanticsNodes().count() == generateOompaLoompasList().count { it.gender == "M" }
        }
    }

    @Test
    fun test_filter_by_female_gender() {
        composeTestRule.onNodeWithTag("main_screen_oompa_table").assertIsDisplayed()
        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("main_screen_ommpa_card")
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("filter_menu_button").performClick()
        composeTestRule.onNodeWithTag("filter_female_rb").performClick()

        composeTestRule.waitUntil(10000) {
            composeTestRule
                .onAllNodesWithTag("main_screen_ommpa_card")
                .fetchSemanticsNodes().count() == generateOompaLoompasList().count { it.gender == "F" }
        }
    }

    @Test
    fun test_filter_by_profession_no_case_sensitive() {
        composeTestRule.onNodeWithTag("main_screen_oompa_table").assertIsDisplayed()
        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("main_screen_ommpa_card")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithTag("filter_menu_button").performClick()
        composeTestRule.onNodeWithTag("filter_profession_te").performTextInput("cow")
        composeTestRule.onNodeWithTag("filter_female_rb").performClick()

        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("main_screen_ommpa_card")
                .fetchSemanticsNodes().count() == generateOompaLoompasList().count { it.profession.lowercase().contains("cow") && it.gender == "F" }
        }

    }

    @Test
    fun test_filter_by_profession_no_case_sensitive_and_gender() {
        composeTestRule.onNodeWithTag("main_screen_oompa_table").assertIsDisplayed()
        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("main_screen_ommpa_card")
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithTag("filter_menu_button").performClick()
        composeTestRule.onNodeWithTag("filter_profession_te").performTextInput("cow")

        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("main_screen_ommpa_card")
                .fetchSemanticsNodes().count() == generateOompaLoompasList().count { it.profession.lowercase() == "cow" }
        }

    }

    private fun generateOompaLoompasList(): List<OompaLoompaInfo> =
        listOf(
            generateOompa(1, "Margarita", "Cow", "F"),
            generateOompa(2, "March", "Cow", "F"),
            generateOompa(3, "Fernandisco", "Dog", "M"),
            generateOompa(4, "Jose Luis", "Sheep", "M"),
            generateOompa(5, "Firulais", "Dog", "M"),
            generateOompa(6, "Hermenegilda", "Cow", "F"),
            generateOompa(7, "Gertrudis", "Sheep", "F")
        )

    private fun generateOompa(id: Int, name: String, profession: String, gender: String) = OompaLoompaInfo(
        id = id,
        firstName = name,
        lastName = "The $profession",
        image = "https://fakeUrl.com",
        email = "a@a",
        age = 56,
        profession = profession,
        height = 56,
        country = "Spain",
        gender = gender,
        tastes = Tastes(
            color = "blue",
            food = "grass",
            randomString = "jashdfkjashdfkjashfkasrg",
            song = "La macarena"
        )
    )
}