package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dao.PostDao
import ru.netology.nmedia.dto.Post

class PostRepositorySQLiteImpl(
    private val dao: PostDao
) : PostRepository {
    private var posts = emptyList<ru.netology.nmedia.dto.Post>()
    private val data = MutableLiveData(posts)

    init {
        posts = dao.get()
        data.value = posts
    }

    override fun get(): LiveData<List<Post>> = data

    override fun save(post: Post) {
        val id = post.id
        val saved = dao.save(post)
        posts = if (id == 0) {
            listOf(saved) + posts
        } else {
            posts.map {
                if (it.id != id) it else saved
            }
        }
        data.value = posts
    }

    override fun like(id: Int) {
        dao.likeById(id)
        posts = posts.map {
            if (it.id != id) it else it.copy(
                likeByMe = !it.likeByMe,
                countLike = if (it.likeByMe) it.countLike - 1 else it.countLike + 1
            )
        }
        data.value = posts
    }

    override fun share(id: Int) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                countShare = it.countShare + 1
            )
        }
        data.value = posts
    }

    override fun removeById(id: Int) {
        dao.removeById(id)
        posts = posts.filter { it.id != id }
        data.value = posts
    }
}