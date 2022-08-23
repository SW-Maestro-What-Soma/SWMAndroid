package com.example.swmandroid.ui.community.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swmandroid.databinding.ItemQuestionBinding
import com.example.swmandroid.model.community.question.QuestionItem
import com.example.swmandroid.util.MetricsUtil.dp

class QuestionAdapter(
    private val dataList: List<QuestionItem>,
    private val isFullCommunity: Boolean,
) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemQuestionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        with(holder) {
            with(binding) {
                //TODO 백엔드 수정해야함
                questionTitle.text = item.title
                questionNickTextview.text = "김시진"
                questionTierTextview.text = "실버 V"
                createdAtTextview.text = item.createdAt.split(" ")[0]
                viewsTextview.text = item.viewCount.toString()

                if (item.commentCount > 0) {
                    commentCountTextview.visibility = View.VISIBLE
                    commentCountTextview.text = "(${item.commentCount})"
                }

                val maxWidth = getTitleMaxWidth()
                questionTitle.maxWidth = maxWidth

                if (position == dataList.size - 1) {
                    bottomContour.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    private fun getTitleMaxWidth(): Int = if (isFullCommunity) 250.dp else 280.dp

}