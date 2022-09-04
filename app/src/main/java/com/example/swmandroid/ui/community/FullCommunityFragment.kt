package com.example.swmandroid.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentFullCommunityBinding
import com.example.swmandroid.model.community.jobposting.JobPostingItem
import com.example.swmandroid.model.community.jobreview.JobReviewItem
import com.example.swmandroid.model.community.question.QuestionItem
import com.example.swmandroid.model.community.study.StudyItem
import com.example.swmandroid.ui.community.adapter.JobPostingAdapter
import com.example.swmandroid.ui.community.adapter.JobReviewAdapter
import com.example.swmandroid.ui.community.adapter.QuestionAdapter
import com.example.swmandroid.ui.community.adapter.StudyAdapter
import com.example.swmandroid.util.Resource
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FullCommunityFragment : BaseFragment<FragmentFullCommunityBinding>() {

    private val communityViewModel: CommunityViewModel by sharedViewModel()

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFullCommunityBinding {
        return FragmentFullCommunityBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initProgressView()
        initView()
        buttonClick()
    }

    fun moveSubCommunityFragment() {
        binding.root.findNavController().navigate(R.id.action_fullCommunityFragment_to_subCommunityFragment)
    }

    private fun initProgressView() = with(binding) {
        jobreviewProgress.hide()
        studyProgress.hide()
        questionProgress.hide()
    }

    private fun initView() {
        makeJobPostingView()
        makeJobReviewView()
        makeStudyView()
        makeQuestionView()
    }

    private fun makeJobPostingView() = with(binding) {
        communityViewModel.getJobPostingList("ALL", 0, 3)
        communityViewModel.jobPostingList.observe(viewLifecycleOwner) { jobPostingResponse ->
            when (jobPostingResponse) {
                is Resource.Loading -> {
                    jobpostingProgress.show()
                }
                is Resource.Success -> {
                    jobpostingProgress.hide()
                    val jobPostingList = jobPostingResponse.data?.jobPostingList?.content
                    connectJobPostingAdapter(jobPostingList ?: emptyList())
                }
                is Resource.Error -> {
                    jobpostingProgress.hide()
                    Toast.makeText(requireContext(), "채용공고 게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun connectJobPostingAdapter(data: List<JobPostingItem>) = with(binding) {
        val adapter = JobPostingAdapter(data, true)

        jobpostingRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }
    }

    private fun makeJobReviewView() = with(binding) {
        communityViewModel.getJobReviewList("ALL", 0, 3)
        communityViewModel.jobReviewList.observe(viewLifecycleOwner) { jobReviewResponse ->
            when (jobReviewResponse) {
                is Resource.Loading -> {
                    jobreviewProgress.show()
                }
                is Resource.Success -> {
                    jobreviewProgress.hide()
                    val jobReviewList = jobReviewResponse.data?.jobReviewList?.content
                    connectJobReviewAdapter(jobReviewList ?: emptyList())
                }
                is Resource.Error -> {
                    jobreviewProgress.hide()
                    Toast.makeText(requireContext(), "채용후기 게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun connectJobReviewAdapter(data: List<JobReviewItem>) = with(binding) {
        val adapter = JobReviewAdapter(data, true)

        jobreviewRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }
    }

    private fun makeStudyView() = with(binding) {
        communityViewModel.getStudyList("ALL", 0, 3)
        communityViewModel.studyList.observe(viewLifecycleOwner) { studyResponse ->
            when (studyResponse) {
                is Resource.Loading -> {
                    studyProgress.show()
                }
                is Resource.Success -> {
                    studyProgress.hide()
                    val studyList = studyResponse.data?.studyList?.content
                    connectStudyAdapter(studyList ?: emptyList())
                }
                is Resource.Error -> {
                    studyProgress.hide()
                    Toast.makeText(requireContext(), "스터디 게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun connectStudyAdapter(data: List<StudyItem>) = with(binding) {
        val adapter = StudyAdapter(data, true)

        studyRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }
    }

    private fun makeQuestionView() = with(binding) {
        communityViewModel.getQuestionList("ALL", 0, 3)
        communityViewModel.questionList.observe(viewLifecycleOwner) { questionResponse ->
            when (questionResponse) {
                is Resource.Loading -> {
                    questionProgress.show()
                }
                is Resource.Success -> {
                    questionProgress.hide()
                    val questionList = questionResponse.data?.nowQnaList?.content
                    connectQuestionAdapter(questionList ?: emptyList())
                }
                is Resource.Error -> {
                    questionProgress.hide()
                    Toast.makeText(requireContext(), "질문 게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun connectQuestionAdapter(data: List<QuestionItem>) = with(binding) {
        val adapter = QuestionAdapter(data, true)

        questionRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }
    }

    private fun buttonClick() = with(binding) {
        jobpostingMoreTextview.setOnClickListener {
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