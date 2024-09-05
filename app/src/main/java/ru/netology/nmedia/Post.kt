package ru.netology.nmedia

import kotlin.math.floor

class Post(
    val id: Int,
    val author: String,
    val content: String,
    val published: String,
    var countLike: Int,
    var countShare: Int,
    var views: Int,
    var likeByMe: Boolean = false
)

val post = Post(
    1, "Нетология. Университет интернет профессий",
    "<Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
    "4 сентября в 18:00",
    5,
    2,
    10
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
