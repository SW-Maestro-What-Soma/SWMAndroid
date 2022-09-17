package com.example.swmandroid.ui.community.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swmandroid.R
import com.example.swmandroid.databinding.ItemQuestionBinding
import com.example.swmandroid.model.community.question.QuestionItem
import com.example.swmandroid.util.MetricsUtil.dp

class QuestionAdapter(
    private val isFullCommunity: Boolean,
) : ListAdapter<QuestionItem, QuestionAdapter.ViewHolder>(diffUtil) {

    var onItemClick : ((QuestionItem) -> Unit)?  = null

    inner class ViewHolder(val binding: ItemQuestionBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : QuestionItem, isLast : Boolean, res : Resources){
            binding.apply {
                questionTitle.text = item.title
                questionNickTextview.text = "김시진"
                questionTierTextview.text = "실버 V"
                createdAtTextview.text = item.createdAt.split(" ")[0]
                viewsTextview.text = item.viewCount.toString()

                if (item.commentCount > 0) {
                    commentCountTextview.visibility = View.VISIBLE
                    commentCountTextview.text = res.getString(R.string.comment_count_text, item.commentCount)
                }

                val maxWidth = getTitleMaxWidth()
                questionTitle.maxWidth = maxWidth

                if (isLast) {
                    bottomContour.visibility = View.INVISIBLE
                }
            }

        }
    }

    private fun getTitleMaxWidth(): Int = if (isFullCommunity) 250.dp else 280.dp

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(currentList[position], position == currentList.size -1, holder.itemView.resources)
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(currentList[holder.adapterPosition])
        }
    }

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<QuestionItem>(){
            override fun areItemsTheSame(oldItem: QuestionItem, newItem: QuestionItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: QuestionItem, newItem: QuestionItem): Boolean {
                return oldItem == newItem
            }

        }
    }

}