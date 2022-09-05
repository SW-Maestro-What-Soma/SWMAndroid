package com.example.swmandroid.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.data.entity.RecentSearchEntity
import com.example.swmandroid.databinding.FragmentSearchCommunityBinding
import com.example.swmandroid.ui.community.adapter.SearchAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchCommunityFragment : BaseFragment<FragmentSearchCommunityBinding>() {

    private val communityViewModel: CommunityViewModel by sharedViewModel()

    private lateinit var adapter: SearchAdapter

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSearchCommunityBinding {
        return FragmentSearchCommunityBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        communityViewModel.techStack.observe(viewLifecycleOwner) { techStack ->
            initView(communityViewModel.categoryData, techStack)
            buttonClick(communityViewModel.categoryData, techStack)
        }

    }

    private fun initView(category: String, techStack: String) = with(binding) {
        (activity as CommunityActivity).topButtonClickBlock(false)

        searchEdittext.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                communityViewModel.addRecentSearchData(RecentSearchEntity(category, techStack, searchEdittext.text.toString()))
                searchEdittext.text.clear()
                true
            } else {
                false
            }
        }

        connectSearchAdapter(category, techStack)
    }

    private fun connectSearchAdapter(category: String, techStack: String) = with(binding) {
        communityViewModel.getAllRecentSearchData(category, techStack)

        adapter = SearchAdapter(
            communityViewModel.recentSearchLiveData.value ?: emptyList(),
            onClickDeleteIcon = { recentSearch ->
                communityViewModel.removeRecentSearchData(recentSearch)
            }
        )

        recentSearchRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recentSearchRecyclerview.adapter = adapter

        communityViewModel.recentSearchLiveData.observe(viewLifecycleOwner) {
            (recentSearchRecyclerview.adapter as SearchAdapter).setData(it)
        }
    }

    private fun buttonClick(category: String, techStack: String) = with(binding) {
        cancelTextview.setOnClickListener {
            searchEdittext.text.clear()
        }

        allDeleteButton.setOnClickListener {
            communityViewModel.removeAllRecentSearchData(category, techStack)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as CommunityActivity).topButtonClickBlock(true)
    }

}