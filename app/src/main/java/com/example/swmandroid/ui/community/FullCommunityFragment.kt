package com.example.swmandroid.ui.community

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentFullCommunityBinding
import com.example.swmandroid.ui.community.adapter.JobPostingAdapter
import com.example.swmandroid.ui.community.adapter.JobReviewAdapter
import com.example.swmandroid.ui.community.adapter.QuestionAdapter
import com.example.swmandroid.ui.community.adapter.StudyAdapter
import com.example.swmandroid.ui.community.detail.DetailJobPostingActivity
import com.example.swmandroid.ui.community.detail.DetailJobReviewActivity
import com.example.swmandroid.ui.community.detail.DetailQuestionActivity
import com.example.swmandroid.ui.community.detail.DetailStudyActivity
import com.example.swmandroid.util.Resource
import com.example.swmandroid.util.hideProgressCircular
import com.example.swmandroid.util.showProgressCircular
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FullCommunityFragment : BaseFragment<FragmentFullCommunityBinding>() {

    private lateinit var onBackCallBack: OnBackPressedCallback

    private val communityViewModel: CommunityViewModel by sharedViewModel()

    private lateinit var jobPostingAdapter: JobPostingAdapter
    private lateinit var jobReviewAdapter: JobReviewAdapter
    private lateinit var studyAdapter: StudyAdapter
    private lateinit var questionAdapter: QuestionAdapter

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFullCommunityBinding {
        return FragmentFullCommunityBinding.inflate(inflater, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        initCallBack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initProgressView()
        initAdapter()
        initView()
        buttonClick()
    }

    override fun onResume() {
        communityViewModel.getFullJobPostingList()
        communityViewModel.getFullJobReviewList()
        communityViewModel.getFullStudyList()
        communityViewModel.getFullQuestionList()
        super.onResume()
    }

    override fun onDetach() {
        super.onDetach()

        onBackCallBack.remove()
    }

    private fun initCallBack() {
        onBackCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as CommunityActivity).finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackCallBack)
    }

    fun moveSubCommunityFragment() {
        binding.root.findNavController().navigate(R.id.action_fullCommunityFragment_to_subCommunityFragment)
    }

    private fun initProgressView() = with(binding) {
        hideProgressCircular(jobpostingProgress)
        hideProgressCircular(jobreviewProgress)
        hideProgressCircular(studyProgress)
        hideProgressCircular(questionProgress)
    }

    private fun initAdapter() = with(binding) {
        jobPostingAdapter = JobPostingAdapter(true)
        jobpostingRecyclerview.apply {
            adapter = jobPostingAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        jobPostingAdapter.onItemClick = {
            val intent = Intent(requireContext(), DetailJobPostingActivity::class.java)
            intent.putExtra("jobPostingItem", it)
            startActivity(intent)
        }

        jobReviewAdapter = JobReviewAdapter(true)
        jobreviewRecyclerview.apply {
            adapter = jobReviewAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        jobReviewAdapter.onItemClick = {
            val intent = Intent(requireContext(), DetailJobReviewActivity::class.java)
            intent.putExtra("jobReviewItem", it)
            startActivity(intent)
        }

        studyAdapter = StudyAdapter(true)
        studyRecyclerview.apply {
            adapter = studyAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        studyAdapter.onItemClick = {
            val intent = Intent(requireContext(), DetailStudyActivity::class.java)
            intent.putExtra("studyItem", it)
            startActivity(intent)
        }

        questionAdapter = QuestionAdapter(true)
        questionRecyclerview.apply {
            adapter = questionAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        questionAdapter.onItemClick = {
            val intent = Intent(requireContext(), DetailQuestionActivity::class.java)
            intent.putExtra("questionItem", it)
            startActivity(intent)
        }
    }

    private fun initView() {
        makeJobPostingView()
        makeJobReviewView()
        makeStudyView()
        makeQuestionView()
    }

    private fun makeJobPostingView() = with(binding) {
        communityViewModel.fullJobPostingList.observe(viewLifecycleOwner) { jobPostingResponse ->
            when (jobPostingResponse) {
                is Resource.Loading -> {
                    showProgressCircular(jobpostingProgress)
                }
                is Resource.Success -> {
                    hideProgressCircular(jobpostingProgress)
                    val jobPostingList = jobPostingResponse.data?.jobPostingList?.content
                    jobPostingAdapter.submitList(jobPostingList)
                }
                is Resource.Error -> {
                    hideProgressCircular(jobpostingProgress)
                    Toast.makeText(requireContext(), "채용공고 게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun makeJobReviewView() = with(binding) {
        communityViewModel.fullJobReviewList.observe(viewLifecycleOwner) { jobReviewResponse ->
            when (jobReviewResponse) {
                is Resource.Loading -> {
                    showProgressCircular(jobreviewProgress)
                }
                is Resource.Success -> {
                    hideProgressCircular(jobreviewProgress)
                    val jobReviewList = jobReviewResponse.data?.jobReviewList?.content
                    jobReviewAdapter.submitList(jobReviewList)
                }
                is Resource.Error -> {
                    hideProgressCircular(jobreviewProgress)
                    Toast.makeText(requireContext(), "채용후기 게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun makeStudyView() = with(binding) {
        communityViewModel.fullStudyList.observe(viewLifecycleOwner) { studyResponse ->
            when (studyResponse) {
                is Resource.Loading -> {
                    showProgressCircular(studyProgress)
                }
                is Resource.Success -> {
                    hideProgressCircular(studyProgress)
                    val studyList = studyResponse.data?.studyList?.content
                    studyAdapter.submitList(studyList)
                }
                is Resource.Error -> {
                    hideProgressCircular(studyProgress)
                    Toast.makeText(requireContext(), "스터디 게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun makeQuestionView() = with(binding) {
        communityViewModel.fullQuestionList.observe(viewLifecycleOwner) { questionResponse ->
            when (questionResponse) {
                is Resource.Loading -> {
                    showProgressCircular(questionProgress)
                }
                is Resource.Success -> {
                    hideProgressCircular(questionProgress)
                    val questionList = questionResponse.data?.nowQnaList?.content
                    questionAdapter.submitList(questionList)
                }
                is Resource.Error -> {
                    hideProgressCircular(questionProgress)
                    Toast.makeText(requireContext(), "질문 게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun buttonClick() = with(binding) {
        jobpostingMoreTextview.setOnClickListener {
            communityViewModel.setCategory("채용공고")
            binding.root.findNavController().navigate(R.id.action_fullCommunityFragment_to_subCommunityFragment)
            (activity as CommunityActivity).setTopCategoryPosition(1)
        }

        jobreviewMoreTextview.setOnClickListener {
            communityViewModel.setCategory("채용후기")
            binding.root.findNavController().navigate(R.id.action_fullCommunityFragment_to_subCommunityFragment)
            (activity as CommunityActivity).setTopCategoryPosition(2)
        }

        studyMoreTextview.setOnClickListener {
            communityViewModel.setCategory("스터디")
            binding.root.findNavController().navigate(R.id.action_fullCommunityFragment_to_subCommunityFragment)
            (activity as CommunityActivity).setTopCategoryPosition(3)
        }

        questionMoreTextview.setOnClickListener {
            communityViewModel.setCategory("질문")
            binding.root.findNavController().navigate(R.id.action_fullCommunityFragment_to_subCommunityFragment)
            (activity as CommunityActivity).setTopCategoryPosition(4)
        }

    }

}