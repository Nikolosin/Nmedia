package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.netology.nmedia.dao.PostDao
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.entity.PostEntity

class PostRepositorySQLiteImpl(
    private val dao: PostDao
) : PostRepository {
    override fun get(): LiveData<List<Post>> = dao.get().map { posts ->
        posts.map {
            it.toDto()
        }
    }

    override fun save(post: Post) {
        dao.save(PostEntity.fromDto(post))
    }

    override fun like(id: Int) {
        dao.likeById(id)
    }

    override fun share(id: Int) {
        dao.share(id)
    }

    override fun removeById(id: Int) {
        dao.removeById(id)
    }
}