package com.example.swmandroid.ui.community.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swmandroid.databinding.ItemStudyBinding
import com.example.swmandroid.model.community.StudyItem

class StudyAdapter(
    private val dataList: List<StudyItem>
) : RecyclerView.Adapter<StudyAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemStudyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemStudyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        with(holder) {
            with(binding) {
                studyTitle.text = item.title
                minTierTextview.text = item.minTier
                maxTierTextview.text = item.maxTier
                studyTechCategory.text = item.techStack
                timesWeekTextview.text = item.timesWeek
                dayWeekTextview.text = item.dayWeek
                onOffTextview.text = item.onOff
                studyNickTextview.text = item.nickName
                studyTierTextview.text = item.tier
                createdAtTextview.text = item.createdAt
                viewsTextview.text = item.viewsCount.toString()
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

}