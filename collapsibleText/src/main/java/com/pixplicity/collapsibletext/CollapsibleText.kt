package com.pixplicity.collapsibletext

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

/**
 * A text that will collapse if its length is extended further than a specified amount
 * of lines. It will then ellipsize the text and show a string tag, indicating to the user
 * that there's more text to be shown once tapped. When it's fully expanded a new tag will
 * be added indicating that the text can be collapsed again.
 * If the length of the size fits the number of maxLines, no tags are added.
 *
 * @param collapsedTag the tag showing when the text is collapsed. Default is "show more"
 * @param expandedTag the tag showing when the text is already expanded. Default is "show less"
 * @param maxLines the number of lines the text should fill. Default is 2
 * @param collapsedTagSpace the space and/or characters between the text and the 'collapsedTag'. Default is ellipsis followed by 5 space characters
 * @param expandedTagSpace the space and/or characters between the text and the 'expandedTag'. Default is 5 space characters
 * @param spanStyle the style the 'collapsedTag' and the 'expandedTag' are using. Default is underline and bold.
 * @param modifier the modifier for the view (width, background color etc)
 */
@Composable
fun CollapsibleText(
    text: String,
    modifier: Modifier = Modifier,
    collapsedTag: String = "show more",
    expandedTag: String = "show less",
    maxLines: Int = 2,
    collapsedTagSpace: String = "...     ",
    expandedTagSpace: String = "     ",
    spanStyle: SpanStyle = SpanStyle(
        textDecoration = TextDecoration.Underline,
        fontWeight = FontWeight.Bold
    )
) {
    var collapsedText = buildAnnotatedString { append(text) }
    val expandedText = buildAnnotatedString {
        append(text)
        append(expandedTagSpace)
        pushStyle(spanStyle)
        append(expandedTag)
        pop()
    }

    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(buildAnnotatedString { append(text) }) }
    var allowToggle by remember { mutableStateOf(false) }
    Column(
        modifier
            .toggleable(value = isExpanded, onValueChange = {
                if (allowToggle) {
                    isExpanded = it
                    selectedText = if (isExpanded) expandedText else collapsedText
                }
            })
    ) {
        Text(
            text = selectedText,
            onTextLayout = {
                if (it.lineCount > maxLines) {
                    allowToggle = true
                    val lineEndIndex = it.getLineEnd(maxLines - 1)
                    collapsedText =
                        buildAnnotatedString {
                            append(
                                text.substring(
                                    0,
                                    lineEndIndex - collapsedTag.length - collapsedTagSpace.length
                                )
                            )
                            append(collapsedTagSpace)
                            pushStyle(spanStyle)
                            append(collapsedTag)
                            pop()
                        }
                    selectedText = if (isExpanded) expandedText else collapsedText
                }
            }
        )
    }
}
