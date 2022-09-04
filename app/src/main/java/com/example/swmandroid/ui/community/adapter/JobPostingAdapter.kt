package com.example.swmandroid.ui.community.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swmandroid.R
import com.example.swmandroid.databinding.ItemJobpostingBinding
import com.example.swmandroid.model.community.jobposting.JobPostingItem
import com.example.swmandroid.util.MetricsUtil.dp

class JobPostingAdapter(
    private val dataList: List<JobPostingItem>,
    private val isFullCommunity: Boolean,
) : RecyclerView.Adapter<JobPostingAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemJobpostingBinding) : RecyclerView.ViewHolder(binding.root)

    var onItemClick : ((JobPostingItem) -> Unit)?  = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemJobpostingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        with(holder) {

            itemView.setOnClickListener{
                onItemClick?.invoke(item)
            }

            with(binding) {
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

                if (position == dataList.size - 1) {
                    bottomContour.visibility = View.INVISIBLE
                }

            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    private fun getTitleMaxWidth(): Int = if (isFullCommunity) 230.dp else 260.dp

}