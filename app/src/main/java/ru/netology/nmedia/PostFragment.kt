package ru.netology.nmedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.NewPostFragment.Companion.textArg
import ru.netology.nmedia.StringArg
import ru.netology.nmedia.PostViewModel
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.FragmentPostBinding
import ru.netology.nmedia.formatCount

class PostFragment : Fragment() {

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
                                    if (postId != null) {
                                        viewModel.removeById(postId.toInt())
                                        findNavController().navigateUp()
                                    }
                                    true
                                }

                                R.id.edit -> {
                                    findNavController().navigate(
                                        R.id.action_postFragment_to_newPostFragment,
                                        Bundle().apply {
                                            textArg = post.content
                                        })
                                    true
                                }

                                else -> false
                            }
                        }
                    }.show()
                }
                like.setOnClickListener {
                    if (postId != null) {
                        viewModel.like(postId.toInt())
                    }
                }
                share.setOnClickListener {
                    if (postId != null) {
                        viewModel.share(postId.toInt())
                    }
                }
            }
        }
        return binding.root
    }
}