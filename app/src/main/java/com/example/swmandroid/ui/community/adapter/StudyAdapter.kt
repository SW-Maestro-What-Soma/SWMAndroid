package com.example.swmandroid.ui.community.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swmandroid.databinding.ItemStudyBinding
import com.example.swmandroid.model.community.study.StudyItem
import com.example.swmandroid.util.MetricsUtil.dp

class StudyAdapter(
    private val dataList: List<StudyItem>,
    private val isFullCommunity: Boolean,
) : RecyclerView.Adapter<StudyAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemStudyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemStudyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        with(holder) {
            with(binding) {
                // TODO 유저정보 백엔드 수정
                studyTitle.text = item.title
                minTierTextview.text = item.minGrade
                maxTierTextview.text = item.maxGrade
                studyTechCategory.text = item.techCategory
                timesWeekTextview.text = "주${item.perWeek}회"
                dayWeekTextview.text = item.dayOfTheWeek
                onOffTextview.text = if (item.onOffline) "온라인" else "오프라인"
                studyNickTextview.text = "김시진"
                studyTierTextview.text = "실버 V"
                createdAtTextview.text = item.createdAt.split(" ")[0]
                viewsTextview.text = item.viewCount.toString()

                if (item.commentCount > 0) {
                    commentCountTextview.visibility = View.VISIBLE
                    commentCountTextview.text = "(${item.commentCount})"
                }

                val maxWidth = getTitleMaxWidth()
                studyTitle.maxWidth = maxWidth

                if (position == dataList.size - 1) {
                    bottomContour.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    private fun getTitleMaxWidth(): Int = if (isFullCommunity) 160.dp else 190.dp

}