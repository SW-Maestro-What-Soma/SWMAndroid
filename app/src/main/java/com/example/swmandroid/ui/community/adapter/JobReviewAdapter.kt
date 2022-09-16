package com.example.swmandroid.ui.community.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swmandroid.R
import com.example.swmandroid.databinding.ItemJobreviewBinding
import com.example.swmandroid.model.community.jobreview.JobReviewItem
import com.example.swmandroid.util.MetricsUtil.dp

class JobReviewAdapter(
    private val isFullCommunity: Boolean,
) : ListAdapter<JobReviewItem, JobReviewAdapter.ViewHolder>(diffUtil) {

    var onItemClick: ((JobReviewItem) -> Unit)? = null

    inner class ViewHolder(val binding: ItemJobreviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: JobReviewItem, isLast: Boolean, res : Resources) {
            binding.apply {
                jobreviewTitle.text = item.title
                jobreviewTechCategory.text = item.techStack
                jobreviewProcessTextview.text = item.processCategory
                //TODO 백엔드에서 닉네임 티어 줘야함
                jobreviewNickTextview.text = "김시진"
                jobreviewTierTextview.text = "실버 V"
                createdAtTextview.text = item.createdAt.split(" ")[0]
                viewsTextview.text = item.viewCount.toString()

                if (item.commentCount > 0) {
                    commentCountTextview.visibility = View.VISIBLE
                    commentCountTextview.text = res.getString(R.string.comment_count_text, item.commentCount)
                }

                if (item.passFail) {
                    passFailImageview.setImageResource(R.drawable.pass)
                } else {
                    passFailImageview.setImageResource(R.drawable.fail)
                }

                val maxWidth = getTitleMaxWidth()
                jobreviewTitle.maxWidth = maxWidth

                if (isLast) {
                    bottomContour.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun getTitleMaxWidth(): Int = if (isFullCommunity) 200.dp else 240.dp

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemJobreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], position == currentList.size - 1, holder.itemView.resources)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentList[position])
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<JobReviewItem>() {
            override fun areItemsTheSame(oldItem: JobReviewItem, newItem: JobReviewItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: JobReviewItem, newItem: JobReviewItem): Boolean {
                return oldItem == newItem
            }

        }
    }

}