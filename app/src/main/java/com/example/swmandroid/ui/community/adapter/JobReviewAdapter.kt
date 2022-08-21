package com.example.swmandroid.ui.community.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swmandroid.databinding.ItemJobreviewBinding
import com.example.swmandroid.model.community.JobReviewItem
import com.example.swmandroid.util.MetricsUtil.dp

class JobReviewAdapter(
    private val dataList: List<JobReviewItem>,
    private val isFullCommunity: Boolean,
) : RecyclerView.Adapter<JobReviewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemJobreviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemJobreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        with(holder) {
            with(binding) {
                jobreviewTitle.text = item.title
                jobreviewTechCategory.text = item.techStack
                jobreviewProcessTextview.text = item.process
                jobreviewNickTextview.text = item.nickName
                jobreviewTierTextview.text = item.tier
                createdAtTextview.text = item.createdAt
                viewsTextview.text = item.viewsCount.toString()

                val maxWidth = getTitleMaxWidth()
                jobreviewTitle.maxWidth = maxWidth

                if (position == dataList.size - 1) {
                    bottomContour.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    private fun getTitleMaxWidth(): Int = if (isFullCommunity) 200.dp else 240.dp

}