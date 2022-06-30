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
import com.example.uitestarticle.MainActivity.Companion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ListTest {

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
    putExtra(MainActivity.TEST_TYPE_EXTRA, TestType.LIST)
  }

  @Test
  fun listComposeTest() {
    pause()
    composeTestRule.onNodeWithTag(ListTags.LIST)
      .performScrollToNode(hasAnyDescendant(hasText("item 20")))

    pause()
    composeTestRule.onAllNodesWithTag(ListTags.ITEM)
      .filterToOne(hasAnyDescendant(hasText("item 20")) and hasAnyDescendant(hasTestTag(ListTags.ICON)))
      .assertIsDisplayed()

    pause()
    composeTestRule.onAllNodesWithTag(ListTags.ITEM)
      .filterToOne(hasAnyDescendant(hasText("item 21")) and !hasAnyDescendant(hasTestTag(ListTags.ICON)))
      .assertIsDisplayed()

    composeTestRule.onAllNodesWithTag(ListTags.ITEM)
      .filterToOne(hasAnyDescendant(hasText("item 20")) and hasAnyDescendant(hasTestTag(ListTags.ICON)))
      .onChildren()
      .filterToOne(hasTestTag(ListTags.ICON))
      .performClick()

    pause()
    composeTestRule.onAllNodesWithTag(ListTags.ITEM)
      .filterToOne(hasAnyDescendant(hasText("item 20")) and hasAnyDescendant(hasTestTag(ListTags.ICON)))
      .assertDoesNotExist()

  }

  private fun pause() {
    Thread.sleep(1000)
  }
}


