package com.example.swmandroid.ui.community.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swmandroid.databinding.ItemJobreviewBinding
import com.example.swmandroid.model.community.jobreview.JobReviewItem
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
                jobreviewTechCategory.text = item.techCategory
                jobreviewProcessTextview.text = item.processCategory
                //TODO 백엔드에서 닉네임 티어 줘야함
                jobreviewNickTextview.text = "김시진"
                jobreviewTierTextview.text = "실버 V"
                createdAtTextview.text = item.createdAt
                viewsTextview.text = item.viewCount.toString()

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