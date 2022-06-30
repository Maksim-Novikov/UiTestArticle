package com.example.uitestarticle

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun Counter() {
  Box(Modifier.fillMaxSize()) {
    val count = remember { mutableStateOf(0) }
    Text(
      text = count.value.toString(),
      modifier = Modifier
        .align(Alignment.Center)
        .testTag(CounterTags.TEXT)
    )
    Button(
      onClick = { count.value = count.value + 1 },
      modifier = Modifier
        .align(Alignment.BottomCenter)
        .testTag(CounterTags.BUTTON)
    ) {
      Text(
        text = "Add",
        modifier = Modifier
          .testTag(CounterTags.BUTTON_TEXT)
      )
    }
  }
}

object CounterTags {

  const val TEXT = "CounterTags:TEXT"
  const val BUTTON = "CounterTags:BUTTON"
  const val BUTTON_TEXT = "CounterTags:BUTTON_TEXT"
}