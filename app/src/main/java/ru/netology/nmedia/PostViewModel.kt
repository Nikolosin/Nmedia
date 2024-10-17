package ru.netology.nmedia

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likeByMe = false,
    published = "",
    countLike = 0,
    countShare = 0,
    views = 0
)

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository = PostRepositoryFileImpl(application)
    val data = repository.get()
    val edited = MutableLiveData(empty)

    fun edit(post: Post) {
        edited.value = post

    }

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }

    fun like(id: Int) = repository.like(id)
    fun share(id: Int) = repository.share(id)
    fun removeById(id: Int) = repository.removeById(id)
    fun clean() {
        edited.value = empty
    }

}