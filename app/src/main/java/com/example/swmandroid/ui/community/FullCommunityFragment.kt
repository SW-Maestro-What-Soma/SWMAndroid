package com.example.swmandroid.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentFullCommunityBinding
import com.example.swmandroid.ui.community.adapter.QuestionAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FullCommunityFragment : BaseFragment<FragmentFullCommunityBinding>() {

    private val communityViewModel: CommunityViewModel by sharedViewModel()

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFullCommunityBinding {
        return FragmentFullCommunityBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //connectJobReviewAdapter()
       // connectStudyAdapter()
        //connectQuestionAdapter()
        buttonClick()
    }

    fun moveSubCommunityFragment() {
        binding.root.findNavController().navigate(R.id.action_fullCommunityFragment_to_subCommunityFragment)
    }

 /*   private fun connectJobReviewAdapter() = with(binding) {
        val adapter = JobReviewAdapter(getJobReviewItem(), true)

        jobreviewRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        jobreviewRecyclerview.adapter = adapter
    }*/

//    private fun connectStudyAdapter() = with(binding) {
//        val adapter = StudyAdapter(getStudyItem(), true)
//
//        studyRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
//        studyRecyclerview.adapter = adapter
//    }

    /*private fun connectQuestionAdapter() = with(binding) {
        val adapter = QuestionAdapter(getQuestionItem(), true)

        questionRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        questionRecyclerview.adapter = adapter
    }*/

    private fun buttonClick() = with(binding) {
        moreJobpostingButton.setOnClickListener {
            communityViewModel.setCategory("채용공고")
            binding.root.findNavController().navigate(R.id.action_fullCommunityFragment_to_subCommunityFragment)
            (activity as CommunityActivity).setTopCategoryPosition(0)
        }

        jobreviewMoreTextview.setOnClickListener {
            communityViewModel.setCategory("채용후기")
            binding.root.findNavController().navigate(R.id.action_fullCommunityFragment_to_subCommunityFragment)
            (activity as CommunityActivity).setTopCategoryPosition(1)
        }

        studyMoreTextview.setOnClickListener {
            communityViewModel.setCategory("스터디")
            binding.root.findNavController().navigate(R.id.action_fullCommunityFragment_to_subCommunityFragment)
            (activity as CommunityActivity).setTopCategoryPosition(2)
        }

        questionMoreTextview.setOnClickListener {
            communityViewModel.setCategory("질문")
            binding.root.findNavController().navigate(R.id.action_fullCommunityFragment_to_subCommunityFragment)
            (activity as CommunityActivity).setTopCategoryPosition(3)
        }

    }

}