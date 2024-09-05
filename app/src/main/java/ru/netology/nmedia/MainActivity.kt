package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bilding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bilding.root)
        with(bilding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            countLike.text = post.countLike.toString()
            countShare.text = post.countShare.toString()
            countViews.text = post.views.toString()

            favorite.setOnClickListener {
                post.likeByMe = !post.likeByMe
                if (post.likeByMe) {
                    favorite.setImageResource(R.drawable.baseline_favorite_true_24)
                    post.countLike++
                } else {
                    favorite.setImageResource(R.drawable.baseline_favorite_24)
                    post.countLike--
                }
                countLike.text = formatCount(post.countLike)
            }

            share.setOnClickListener {
                post.countShare++
                countShare.text = formatCount(post.countShare)
            }

        }
    }

}