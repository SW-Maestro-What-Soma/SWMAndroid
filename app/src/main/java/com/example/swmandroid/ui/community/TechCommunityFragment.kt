package com.example.swmandroid.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentTechCommunityBinding
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

class TechCommunityFragment : BaseFragment<FragmentTechCommunityBinding>() {

    private val communityViewModel: CommunityViewModel by sharedViewModel()

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTechCommunityBinding {
        return FragmentTechCommunityBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        communityViewModel.category.observe(viewLifecycleOwner) { category ->
            communityViewModel.techStack.observe(viewLifecycleOwner) { techStack ->
                when (category) {
                    "채용공고" -> {
                        makeJobPostingView(techStack)
                    }
                    "채용후기" -> {
                        makeJobReviewView(techStack)
                    }
                    "스터디" -> {
                        makeStudyView(techStack)
                    }
                    "질문" -> {
                        makeQuestionView(techStack)
                    }
                }
            }
        }
    }

    private fun makeJobPostingView(techStack: String) = with(binding) {
        communityViewModel.getJobPostingList(techStack, 0, 10)
        communityViewModel.jobPostingList.observe(viewLifecycleOwner) { jobPostingResponse ->
            when (jobPostingResponse) {
                is Resource.Loading -> {
                    progressCircular.show()
                }
                is Resource.Success -> {
                    progressCircular.hide()
                    val jobPostingListTechStack = jobPostingResponse.data?.jobPostingList?.content
                    connectJobPostingAdapter(jobPostingListTechStack ?: emptyList())
                }
                is Resource.Error -> {
                    progressCircular.hide()
                    Toast.makeText(requireContext(), "채용공고 게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun makeJobReviewView(techStack: String) = with(binding) {
        communityViewModel.getJobReviewList(techStack, 0, 10)
        communityViewModel.jobReviewList.observe(viewLifecycleOwner) { jobReviewResponse ->
            when (jobReviewResponse) {
                is Resource.Loading -> {
                    progressCircular.show()
                }
                is Resource.Success -> {
                    progressCircular.hide()
                    val jobReviewListTechStack = jobReviewResponse.data?.jobReviewList?.content
                    connectJobReviewAdapter(jobReviewListTechStack ?: emptyList())
                }
                is Resource.Error -> {
                    progressCircular.hide()
                    Toast.makeText(requireContext(), "채용후기 게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun makeStudyView(techStack: String) = with(binding) {
        communityViewModel.getStudyList(techStack, 0, 10)
        communityViewModel.studyList.observe(viewLifecycleOwner) { studyResponse ->
            when (studyResponse) {
                is Resource.Loading -> {
                    progressCircular.show()
                }
                is Resource.Success -> {
                    progressCircular.hide()
                    val studyListTechStack = studyResponse.data?.studyList?.content
                    connectStudyAdapter(studyListTechStack ?: emptyList())
                }
                is Resource.Error -> {
                    progressCircular.hide()
                    Toast.makeText(requireContext(), "스터디 게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun makeQuestionView(techStack: String) = with(binding) {
        communityViewModel.getQuestionList(techStack, 0, 10)
        communityViewModel.questionList.observe(viewLifecycleOwner) { questionResponse ->
            when (questionResponse) {
                is Resource.Loading -> {
                    progressCircular.show()
                }
                is Resource.Success -> {
                    progressCircular.hide()
                    val questionListTechStack = questionResponse.data?.nowQnaList?.content
                    connectQuestionAdapter(questionListTechStack ?: emptyList())
                }
                is Resource.Error -> {
                    progressCircular.hide()
                    Toast.makeText(requireContext(), "질문 게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun connectJobPostingAdapter(data: List<JobPostingItem>) = with(binding) {
        val adapter = JobPostingAdapter(data, false)

        subCommunityRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        subCommunityRecyclerview.adapter = adapter
    }

    private fun connectJobReviewAdapter(data: List<JobReviewItem>) = with(binding) {
        val adapter = JobReviewAdapter(data, false)

        subCommunityRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        subCommunityRecyclerview.adapter = adapter
    }

    private fun connectStudyAdapter(data: List<StudyItem>) = with(binding) {
        val adapter = StudyAdapter(data, false)

        subCommunityRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        subCommunityRecyclerview.adapter = adapter
    }

    private fun connectQuestionAdapter(data: List<QuestionItem>) = with(binding) {
        val adapter = QuestionAdapter(data, false)

        subCommunityRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        subCommunityRecyclerview.adapter = adapter
    }

}