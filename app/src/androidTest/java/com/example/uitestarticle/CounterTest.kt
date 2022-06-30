package com.example.uitestarticle

import android.content.Intent
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.test.assertAll
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.uitestarticle.MainActivity.Companion.TEST_TYPE_EXTRA
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CounterTest {

  @get:Rule
  val activityScenarioRule = ActivityScenarioRule<MainActivity>(createIntent())

  @get:Rule
  val composeTestRule = AndroidComposeTestRule(activityScenarioRule) {
    var activity: MainActivity? = null
    it.scenario.onActivity { activity = it }
    activity!!
  }

  private fun createIntent() = Intent(
    InstrumentationRegistry.getInstrumentation().targetContext,
    MainActivity::class.java
  ).apply {
    putExtra(TEST_TYPE_EXTRA, TestType.COUNTER)
    // Modify your Intent here
  }

//  @get:Rule
//  val composeTestRule = createAndroidComposeRule<MainActivity>()

  @Test
  fun counterComposeTest() {
    composeTestRule.onRoot().printToLog("TAG")
    pause()
    composeTestRule.onNodeWithTag(CounterTags.BUTTON).performClick()
    composeTestRule.onNodeWithTag(CounterTags.TEXT).assertTextEquals("1")
    pause()
    composeTestRule.onNodeWithTag(CounterTags.BUTTON).performClick()
    composeTestRule.onNodeWithTag(CounterTags.TEXT).assertTextEquals("2")
    composeTestRule.onRoot(useUnmergedTree = false).printToLog("Tag")
    composeTestRule.onRoot(useUnmergedTree = true).printToLog("Tag")

    composeTestRule.onNodeWithTag(CounterTags.BUTTON_TEXT).assertTextEquals("Add")
  }

  private fun pause() {
    Thread.sleep(1000)
  }
}


