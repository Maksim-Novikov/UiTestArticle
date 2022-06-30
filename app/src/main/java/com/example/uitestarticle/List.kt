package com.example.uitestarticle

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ListExample() {
  val data = remember { mutableStateOf((1 until 100).map { it.toString() }) }
  LazyColumn(Modifier.testTag(ListTags.LIST)) {
    items(data.value, { it }) { item ->
      Row(
        modifier = Modifier
          .padding(10.dp)
          .testTag(ListTags.ITEM)
      ) {
        Text("item $item", Modifier.testTag(ListTags.TEXT))
        if (item.toInt() % 5 == 0) {
          Image(
            painter = painterResource(R.drawable.ic_error),
            contentDescription = null,
            modifier = Modifier
              .testTag(ListTags.ICON)
              .clickable { data.value = data.value.filter { it != item } },
          )
        }
      }
    }
  }
}

object ListTags {

  const val LIST = "ListTags:LIST"
  const val TEXT = "ListTags:TEXT"
  const val ITEM = "ListTags:ITEM"
  const val ICON = "ListTags:ICON"
}