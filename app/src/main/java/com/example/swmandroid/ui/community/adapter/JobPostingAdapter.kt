package com.example.swmandroid.ui.community.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swmandroid.databinding.ItemJobpostingBinding
import com.example.swmandroid.model.community.JobPostingItem

class JobPostingAdapter(
    private val dataList: List<JobPostingItem>
) : RecyclerView.Adapter<JobPostingAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemJobpostingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemJobpostingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        with(holder){
            with(binding){
                jobpostingTitle.text = item.title
                jobpostingTechCategory.text = item.techStack
                jobpostingStartEndTime.text = item.date
                createdAtTextview.text = item.createdAt
                viewsTextview.text = item.viewsCount.toString()
            }
        }
    }

    override fun getItemCount(): Int = dataList.size
}