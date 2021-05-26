package com.pixplicity.collapsibletext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pixplicity.collapsibletext.ui.theme.CollapsibleTextTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp { CollapsibleTextScreen() }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    CollapsibleTextTheme {
        Surface(color = Color.White) {
            content()
        }
    }
}

/**
 * Example usage of the CollapsibleText component.
 */
@Composable
fun CollapsibleTextScreen() {
    val texts = listOf(
        "a dummy text that should show full in two lines",
        "a dummy text that should not be too long to show entirely in juuust two lines",
        "a dummy text that should be too long to show entirely in two lines and should show the expand buttons",
        "a dummy text that should be too long to show entirely in two lines and should show the expand buttons",
        "a dummy text that should be too long to show entirely in two lines and should show the expand buttons",
        "a dummy text that should be too long to show entirely in two lines and should show the expand buttons"
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .width(300.dp)
            .padding(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        items(items = texts) { text ->
            CollapsibleText(
                text = text,
                modifier = Modifier.width(300.dp),
                collapsedTag = "show more",
                expandedTag = "show less",
                maxLines = 2
            )
            Divider(color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CollapsibleTextTheme {
        CollapsibleTextScreen()
    }
}