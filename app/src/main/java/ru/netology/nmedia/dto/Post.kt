package ru.netology.nmedia.dto

import kotlin.math.floor

data class Post(
    val id: Int,
    val author: String,
    val content: String,
    val published: String,
    val countLike: Int,
    val countShare: Int,
    val views: Int,
    val likeByMe: Boolean = false,
    val url: String = ""
)

fun formatCount(count: Int): String {
    return if (count < 1000) {
        count.toString()
    } else if (count < 10000) {
        (floor(count / 1000.0 * 10.0) / 10).toString() + "K"
    } else if (count < 1000000) {
        (count / 1000).toString() + "K"
    } else {
        (floor(count / 1000000.0 * 10) / 10).toString() + "M"
    }
}