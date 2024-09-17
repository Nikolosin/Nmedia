package ru.netology.nmedia

import androidx.lifecycle.LiveData

interface PostRepository {
    fun get(): LiveData<List<Post>>
    fun like(id: Int)
    fun share(id: Int)
    fun removeById(id: Int)
    fun save(post: Post)
}