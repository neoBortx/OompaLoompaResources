package com.bortxapps.oompaloomparesources.endtoend

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bortxapps.data.OompaLoompaInfo
import com.bortxapps.data.Tastes
import com.bortxapps.oompaloomparesources.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.*
import org.junit.*
import org.junit.runner.*

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainScreenEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun test_end_to_end_navigate_to_detail() {


        composeTestRule.onNodeWithTag("main_screen_oompa_table").assertIsDisplayed()
        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("main_screen_ommpa_card")
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onAllNodesWithTag("main_screen_ommpa_card").onFirst().performClick()
        composeTestRule.waitUntil {
            composeTestRule
                .onAllNodesWithTag("oompa_detail_layout").fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.onNodeWithTag("oompa_detail_name").assertIsDisplayed()
        composeTestRule.onNodeWithTag("oompa_detail_surname").assertIsDisplayed()
        composeTestRule.onNodeWithTag("oompa_detail_gender").assertIsDisplayed()
        composeTestRule.onNodeWithTag("oompa_detail_profession").assertIsDisplayed()
        composeTestRule.onNodeWithTag("oompa_detail_email").assertIsDisplayed()
        composeTestRule.onNodeWithTag("oompa_detail_country").assertIsDisplayed()
        composeTestRule.onNodeWithTag("oompa_detail_age").assertIsDisplayed()
        composeTestRule.onNodeWithTag("oompa_detail_color").assertIsDisplayed()
        composeTestRule.onNodeWithTag("oompa_detail_food").assertIsDisplayed()

    }

    private fun generateOompa(): OompaLoompaInfo =
        OompaLoompaInfo(
            id = 5,
            firstName = "Margarita",
            lastName = "The cow",
            image = "https://fakeUrl.com",
            email = "a@a",
            age = 56,
            profession = "cow",
            height = 56,
            country = "Spain",
            gender = "M",
            tastes = Tastes(
                color = "blue",
                food = "grass",
                randomString = "jashdfkjashdfkjashfkasrg",
                song = "La macarena"
            )
        )


}