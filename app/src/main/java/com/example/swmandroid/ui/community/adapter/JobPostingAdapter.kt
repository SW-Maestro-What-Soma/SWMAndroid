package com.example.swmandroid.ui.community.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swmandroid.databinding.ItemJobpostingBinding
import com.example.swmandroid.model.community.jobposting.JobPostingItem
import com.example.swmandroid.util.MetricsUtil.dp

class JobPostingAdapter(
    private val dataList: List<JobPostingItem>,
    private val isFullCommunity: Boolean,
) : RecyclerView.Adapter<JobPostingAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemJobpostingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemJobpostingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        with(holder) {
            with(binding) {
                jobpostingTitle.text = item.title
                jobpostingTechCategory.text = item.techCategory
                jobpostingStartEndTime.text = item.startEndTime
                createdAtTextview.text = item.createdAt
                viewsTextview.text = item.viewCount.toString()

                jobpostingTitle.maxWidth = 260.dp

                if (position == dataList.size - 1) {
                    bottomContour.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

}