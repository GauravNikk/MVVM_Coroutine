package com.demo.network.responseModels.home

data class ImageResponseModelItem(
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)