package com.example.swmandroid.ui.community

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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

    private var techStack = ""
    private var sort = ""

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTechCommunityBinding {
        return FragmentTechCommunityBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initSearchEdittext()
        buttonClick()
    }

    override fun onResume() {
        super.onResume()

        communityViewModel.techStack.observe(viewLifecycleOwner) { techStack ->
            this.techStack = techStack

            communityViewModel.sort.observe(viewLifecycleOwner) { sort ->
                this.sort = sort

                if (sort == "id,DESC") {
                    setNewOrderTextView()
                } else {
                    setViewOrderTextView()
                }

                val keyword = binding.searchEdittext.text.toString()

                if (keyword.isBlank()) {
                    getDataList(communityViewModel.categoryData, techStack, sort)
                } else {
                    getSearchList(communityViewModel.categoryData, keyword, techStack, sort)
                }
            }
        }
    }

    private fun initView() {
        when (communityViewModel.categoryData) {
            "채용공고" -> makeJobPostingView()
            "채용후기" -> makeJobReviewView()
            "스터디" -> makeStudyView()
            "질문" -> makeQuestionView()
        }
    }

    private fun initSearchEdittext() {
        binding.searchEdittext.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val keyword = binding.searchEdittext.text.toString()

                if (keyword.isNotBlank()) {
                    getSearchList(communityViewModel.categoryData, keyword, techStack, sort)
                } else {
                    getDataList(communityViewModel.categoryData, techStack, sort)
                }

                hideKeyBoard()

                true
            }
            false
        }
    }

    private fun getDataList(category: String, techStack: String, sort: String) {
        when (category) {
            "채용공고" -> communityViewModel.getJobPostingList(techStack, 0, 10, sort)
            "채용후기" -> communityViewModel.getJobReviewList(techStack, 0, 10, sort)
            "스터디" -> communityViewModel.getStudyList(techStack, 0, 10, sort)
            "질문" -> communityViewModel.getQuestionList(techStack, 0, 10, sort)
        }
    }

    private fun getSearchList(category: String, keyword: String, techStack: String, sort: String) {
        when (category) {
            "채용공고" -> communityViewModel.getSearchQna(keyword, 0, 10, sort)
            "채용후기" -> communityViewModel.getSearchQna(keyword, 0, 10, sort)
            "스터디" -> communityViewModel.getSearchQna(keyword, 0, 10, sort)
            "질문" -> communityViewModel.getSearchQna(keyword, 0, 10, sort)
        }
    }

    private fun hideKeyBoard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.searchEdittext.windowToken, 0)
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

    private fun makeJobPostingView() = with(binding) {
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

    private fun makeJobReviewView() = with(binding) {
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

    private fun makeStudyView() = with(binding) {
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

    private fun makeQuestionView() = with(binding) {
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