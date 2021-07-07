package com.pixplicity.collapsibletext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
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
 * A more complex example usage of the CollapsibleText component.
 */
@Composable
fun CollapsibleTextScreen() {
    LazyRow(
        modifier = Modifier
            .wrapContentHeight()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        items(items = createDummyData()) { item ->
            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.size(280.dp, 240.dp)
            ) {
                Column {
                    Image(
                        painter = rememberCoilPainter(
                            request = item.url,
                        ),
                        contentDescription = null, // decorative
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f)
                    )
                    CollapsibleText(
                        text = item.text,
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                            .verticalScroll(state = ScrollState(0)),
                        collapsedTag = "show more",
                        expandedTag = "show less",
                        maxLines = 2
                    )
                }
            }
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