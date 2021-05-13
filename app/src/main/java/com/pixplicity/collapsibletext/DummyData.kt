package com.pixplicity.collapsibletext

import android.util.Log

data class DummyData(
    val url: String,
    val text: String
)

private val texts = listOf(
    "a dummy text that should show full in two lines",
    "a dummy text that should show entirely in juuuuuuuust two lines",
    "a dummy text that should be too long to show entirely in two lines and should show the expand buttons",
    "a dummy text that should be too long to show entirely in two lines and should show the expand buttons",
    "a dummy text that should be too long to show entirely in two lines and should show the expand buttons",
    "a dummy text that should be too long to show entirely in two lines and should show the expand buttons"
)

private val images = listOf(
    "https://images.pexels.com/photos/5252787/pexels-photo-5252787.jpeg",
    "https://images.pexels.com/photos/2523934/pexels-photo-2523934.jpeg",
    "https://images.pexels.com/photos/4490129/pexels-photo-4490129.jpeg",
    "https://images.pexels.com/photos/3478875/pexels-photo-3478875.jpeg",
    "https://images.pexels.com/photos/3908821/pexels-photo-3908821.jpeg",
    "https://images.pexels.com/photos/4587998/pexels-photo-4587998.jpeg"
)

fun createDummyData(): List<DummyData> {
    if (images.size != texts.size) {
        Log.w("DummyData", "createDummyData: number of images and texts don't match")
        return emptyList()
    }
    val dummyList = mutableListOf<DummyData>()
    images.forEachIndexed { index, image -> dummyList.add(DummyData(image, texts[index])) }
    return dummyList
}
