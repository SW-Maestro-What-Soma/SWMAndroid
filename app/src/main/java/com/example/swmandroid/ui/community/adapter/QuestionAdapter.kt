package com.example.swmandroid.ui.community.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swmandroid.databinding.ItemQuestionBinding
import com.example.swmandroid.model.community.QuestionItem

class QuestionAdapter(
    private val dataList : List<QuestionItem>
) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>(){

    inner class ViewHolder(val binding : ItemQuestionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        with(holder){
            with(binding){
                questionTitle.text = item.title
                questionNickTextview.text = item.nickName
                questionTierTextview.text = item.tier
                createdAtTextview.text = item.createdAt
                viewsTextview.text = item.viewsCount.toString()
            }
        }
    }

    override fun getItemCount(): Int = dataList.size
}