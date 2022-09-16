package com.example.swmandroid.ui.community.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.swmandroid.R
import com.example.swmandroid.databinding.ItemStudyBinding
import com.example.swmandroid.model.community.study.StudyItem
import com.example.swmandroid.util.MetricsUtil.dp

class StudyAdapter(
    private val isFullCommunity: Boolean,
) : ListAdapter<StudyItem, StudyAdapter.ViewHolder>(diffUtil) {

    var onItemClick: ((StudyItem) -> Unit)? = null

    inner class ViewHolder(val binding: ItemStudyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StudyItem, isLast: Boolean, res: Resources) {
            binding.apply {
                studyTitle.text = item.title
                minTierTextview.text = item.minGrade
                maxTierTextview.text = item.maxGrade
                studyTechCategory.text = item.techStack
                timesWeekTextview.text = res.getString(R.string.per_week_count_text, item.perWeek)
                dayWeekTextview.text = item.dayOfTheWeek
                onOffTextview.text = if (item.onOffline) "온라인" else "오프라인"
                // TODO 백엔드에서 닉네임 티어 줘야함
                studyNickTextview.text = "김시진"
                studyTierTextview.text = "실버 V"
                createdAtTextview.text = item.createdAt.split(" ")[0]
                viewsTextview.text = item.viewCount.toString()

                if (item.commentCount > 0) {
                    commentCountTextview.visibility = View.VISIBLE
                    commentCountTextview.text = res.getString(R.string.comment_count_text, item.commentCount)
                }

                val maxWidth = getTitleMaxWidth()
                studyTitle.maxWidth = maxWidth

                if (isLast) {
                    bottomContour.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun getTitleMaxWidth(): Int = if (isFullCommunity) 160.dp else 190.dp

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemStudyBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], position == currentList.size - 1, holder.itemView.resources)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentList[position])
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<StudyItem>() {
            override fun areItemsTheSame(oldItem: StudyItem, newItem: StudyItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: StudyItem, newItem: StudyItem): Boolean {
                return oldItem == newItem
            }

        }
    }

}