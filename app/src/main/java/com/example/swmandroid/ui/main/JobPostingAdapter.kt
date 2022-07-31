package com.example.swmandroid.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.swmandroid.R
import com.example.swmandroid.databinding.ItemJobpostingBinding

class JobPostingAdapter(
    private val viewPager: ViewPager2,
    private val jobPostList: ArrayList<JobPostingItem>
) : RecyclerView.Adapter<JobPostingAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(val binding: ItemJobpostingBinding) : RecyclerView.ViewHolder(binding.root) {
        val img = binding.imageSlider
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_jobposting, parent, false)
        return SliderViewHolder(ItemJobpostingBinding.bind(view))
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val item = jobPostList[position]
        holder.img.setImageResource(item.img)
        if (position == jobPostList.size - 2) {
            viewPager.post(run)
        }
    }

    private val run = Runnable {
        jobPostList.addAll(jobPostList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = jobPostList.size
}