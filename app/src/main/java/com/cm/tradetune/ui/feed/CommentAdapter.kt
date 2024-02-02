package com.cm.tradetune.ui.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cm.tradetune.data.model.CommentDto
import com.cm.tradetune.databinding.ItemCommentBinding

class CommentAdapter(private var commentList: List<CommentDto>) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    fun submitList(newCommentList: List<CommentDto>?) {
        if (newCommentList != null) {
            commentList = newCommentList
        }
    }

    class CommentViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: CommentDto) {
            binding.textViewComment.text = comment.commentContent
            binding.textViewUserName.text = comment.userName
            binding.textViewTimestamp.text = comment.timestamp
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(commentList[position])
    }

    override fun getItemCount(): Int = commentList.size
}
