package com.example.swmandroid.ui.community.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swmandroid.R
import com.example.swmandroid.databinding.ItemJobpostingBinding
import com.example.swmandroid.model.community.jobposting.JobPostingItem
import com.example.swmandroid.util.MetricsUtil.dp

class JobPostingAdapter(
    private val isFullCommunity: Boolean,
) : ListAdapter<JobPostingItem, JobPostingAdapter.ViewHolder>(diffUtil) {

    var onItemClick: ((JobPostingItem) -> Unit)? = null

    inner class ViewHolder(val binding: ItemJobpostingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: JobPostingItem, isLast: Boolean) {
            binding.apply {
                jobpostingTitle.text = item.title
                jobpostingTechCategory.text = item.techStack
                jobpostingStartEndTime.text = item.startEndTime
                createdAtTextview.text = item.createdAt.split(" ")[0]
                viewsTextview.text = item.viewCount.toString()

                if (item.career == "신입") {
                    newbieCareerImage.setImageResource(R.drawable.newbie)
                } else {
                    newbieCareerImage.setImageResource(R.drawable.career)
                }

                val maxWidth = getTitleMaxWidth()
                jobpostingTitle.maxWidth = maxWidth

                if (isLast) {
                    bottomContour.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun getTitleMaxWidth(): Int = if (isFullCommunity) 230.dp else 260.dp

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemJobpostingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], position == currentList.size - 1)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentList[position])
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<JobPostingItem>() {
            override fun areItemsTheSame(oldItem: JobPostingItem, newItem: JobPostingItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: JobPostingItem, newItem: JobPostingItem): Boolean {
                return oldItem == newItem
            }

        }
    }

}