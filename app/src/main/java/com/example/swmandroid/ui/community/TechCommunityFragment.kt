package com.example.swmandroid.ui.community

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swmandroid.R
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
import com.example.swmandroid.ui.community.detail.DetailJobPostingActivity
import com.example.swmandroid.ui.community.detail.DetailJobReviewActivity
import com.example.swmandroid.ui.community.detail.DetailQuestionActivity
import com.example.swmandroid.ui.community.detail.DetailStudyActivity
import com.example.swmandroid.ui.community.post.PostJobPostingActivity
import com.example.swmandroid.ui.community.post.PostJobReviewActivity
import com.example.swmandroid.ui.community.post.PostQuestionActivity
import com.example.swmandroid.ui.community.post.PostStudyActivity
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
        buttonClick()
    }

    private fun initView() {
        communityViewModel.techStack.observe(viewLifecycleOwner) { techStack ->
            communityViewModel.sort.observe(viewLifecycleOwner) { sort ->
                if (sort == "id,DESC") {
                    setNewOrderTextView()
                } else {
                    setViewOrderTextView()
                }

                when (communityViewModel.categoryData) {
                    "채용공고" -> {
                        makeJobPostingView(techStack, sort)
                    }
                    "채용후기" -> {
                        makeJobReviewView(techStack, sort)
                    }
                    "스터디" -> {
                        makeStudyView(techStack, sort)
                    }
                    "질문" -> {
                        makeQuestionView(techStack, sort)
                    }
                }
            }
        }
    }

    private fun buttonClick() = with(binding) {
        neworderTextview.setOnClickListener {
            setNewOrderTextView()
            setNewOrderLiveData()
        }

        vieworderTextview.setOnClickListener {
            setViewOrderTextView()
            setViewOrderLiveData()
        }

        writeButton.setOnClickListener {
            startActivity(getIntentByCategory())
        }
    }

    private fun setNewOrderTextView() = with(binding) {
        neworderTextview.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        vieworderTextview.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
    }

    private fun setNewOrderLiveData() {
        communityViewModel.setNewOrder()
    }

    private fun setViewOrderTextView() = with(binding) {
        neworderTextview.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
        vieworderTextview.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
    }

    private fun setViewOrderLiveData() {
        communityViewModel.setViewOrder()
    }

    private fun getIntentByCategory(): Intent {
        return when (communityViewModel.categoryData) {
            "채용공고" -> Intent(requireContext(), PostJobPostingActivity::class.java)
            "채용후기" -> Intent(requireContext(), PostJobReviewActivity::class.java)
            "스터디" -> Intent(requireContext(), PostStudyActivity::class.java)
            "질문" -> Intent(requireContext(), PostQuestionActivity::class.java)
            else -> Intent(requireContext(), PostJobPostingActivity::class.java)
        }
    }

    private fun makeJobPostingView(techStack: String, sort: String) = with(binding) {
        communityViewModel.getJobPostingList(techStack, 0, 10, sort)
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

    private fun makeJobReviewView(techStack: String, sort: String) = with(binding) {
        communityViewModel.getJobReviewList(techStack, 0, 10, sort)
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

    private fun makeStudyView(techStack: String, sort: String) = with(binding) {
        communityViewModel.getStudyList(techStack, 0, 10, sort)
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

    private fun makeQuestionView(techStack: String, sort: String) = with(binding) {
        communityViewModel.getQuestionList(techStack, 0, 10, sort)
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

        adapter.onItemClick = {
            val intent = Intent(requireContext(), DetailJobPostingActivity::class.java)
            intent.putExtra("JobPosting", it)
            startActivity(intent)
        }
    }

    private fun connectJobReviewAdapter(data: List<JobReviewItem>) = with(binding) {
        val adapter = JobReviewAdapter(data, false)

        subCommunityRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        subCommunityRecyclerview.adapter = adapter

        adapter.onItemClick = {
            val intent = Intent(requireContext(), DetailJobReviewActivity::class.java)
            intent.putExtra("JobReview", it)
            startActivity(intent)
        }
    }

    private fun connectStudyAdapter(data: List<StudyItem>) = with(binding) {
        val adapter = StudyAdapter(data, false)

        subCommunityRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        subCommunityRecyclerview.adapter = adapter

        adapter.onItemClick = {
            val intent = Intent(requireContext(), DetailStudyActivity::class.java)
            intent.putExtra("Study", it)
            startActivity(intent)
        }
    }

    private fun connectQuestionAdapter(data: List<QuestionItem>) = with(binding) {
        val adapter = QuestionAdapter(data, false)

        subCommunityRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        subCommunityRecyclerview.adapter = adapter

        adapter.onItemClick = {
            val intent = Intent(requireContext(), DetailQuestionActivity::class.java)
            intent.putExtra("Question", it)
            startActivity(intent)
        }
    }

}