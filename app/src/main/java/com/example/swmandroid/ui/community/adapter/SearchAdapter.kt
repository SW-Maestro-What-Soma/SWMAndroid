package com.example.swmandroid.ui.community.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swmandroid.data.entity.RecentSearchEntity
import com.example.swmandroid.databinding.ItemRecentSearchBinding

class SearchAdapter(
    private var dataList: List<RecentSearchEntity>,
    val onClickDeleteIcon: (data: RecentSearchEntity) -> Unit,
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRecentSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecentSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(binding) {
                recentTextview.text = dataList[position].recentSearch
                deleteButton.setOnClickListener {
                    onClickDeleteIcon.invoke(dataList[position])
                }
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newDataList: List<RecentSearchEntity>) {
        dataList = newDataList
        notifyDataSetChanged()
    }

}