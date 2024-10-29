package ru.netology.nmedia

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun get(): LiveData<List<ru.netology.nmedia.dto.Post>>
    fun like(id: Int)
    fun share(id: Int)
    fun removeById(id: Int)
    fun save(post: Post)
}