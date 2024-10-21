package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.nmedia.StringArg
import ru.netology.nmedia.PostViewModel
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentPostBinding
import ru.netology.nmedia.formatCount

class NewPostFragment : Fragment() {

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPostBinding.inflate(
            inflater,
            container,
            false
        )

        val postId = arguments?.textArg
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            val post = posts.find { it.id == postId?.toInt() } ?: return@observe
            with(binding) {
                if (post.url != "") {
                    image.visibility = View.VISIBLE
                } else image.visibility = View.GONE

                author.text = post.author
                published.text = post.published
                content.text = post.content
                like.isChecked = post.likeByMe
                like.text = formatCount(post.countLike)
                views.text = formatCount(post.views)
                share.text = post.id.toString()
                menu.setOnClickListener {
                    PopupMenu(it.context, it).apply {
                        inflate(R.menu.options_post)
                        setOnMenuItemClickListener { item ->
                            when (item.itemId) {
                                R.id.remove -> {
                                    //onInteractionListener.onRemove(post)
                                    //дорабатываю прямо сейчас
                                    true
                                }

                                R.id.edit -> {
                                    //onInteractionListener.onEdit(post)
                                    true
                                }

                                else -> false
                            }
                        }
                    }.show()
                }
                like.setOnClickListener {
                    //onInteractionListener.onLike(post)
                }
                share.setOnClickListener {
                   // onInteractionListener.onShare(post)
                }
                image.setOnClickListener {
                    //onInteractionListener.onImage(post)
                }
            }
        }
        return binding.root
    }
}