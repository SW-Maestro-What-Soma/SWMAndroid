package com.example.swmandroid.ui.community

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swmandroid.R
import com.example.swmandroid.databinding.FragmentTechCommunityBinding
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
import com.example.swmandroid.util.getUserRoleFromDataStore
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TechCommunityFragment : Fragment() {

    private var _binding: FragmentTechCommunityBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TECH_STACK = "techStack"

        fun newInstance(techStack: String) =
            TechCommunityFragment().apply {
                arguments = Bundle().apply {
                    putString(TECH_STACK, techStack)
                }
            }

    }

    private val communityViewModel: CommunityViewModel by sharedViewModel()

    private lateinit var jobPostingAdapter: JobPostingAdapter
    private lateinit var jobReviewAdapter: JobReviewAdapter
    private lateinit var studyAdapter: StudyAdapter
    private lateinit var questionAdapter: QuestionAdapter

    private var techStack = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTechCommunityBinding.inflate(inflater, container, false)
        binding.test.text = techStack

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListView()
        initSortView()
        initSearchEdittext()
        buttonClick()
        initWriteButtonView()
    }

    override fun onResume() {
        super.onResume()
        techStack = arguments?.getString(TECH_STACK) ?: ""
        callAPI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        when (communityViewModel.categoryData) {
            "채용공고" -> {
                initJobPostingAdapter()
                makeJobPostingView()
            }
            "채용후기" -> {
                initJobReviewAdapter()
                makeJobReviewView()
            }
            "스터디" -> {
                initStudyAdapter()
                makeStudyView()
            }
            "질문" -> {
                initQuestionAdapter()
                makeQuestionView()
            }
        }
    }

    private fun initListView(){
        when(techStack){
            "Backend" -> { jobPostingAdapter.submitList(communityViewModel.allJobPostingList.value?.data?.backend) }
            "Frontend" -> {jobPostingAdapter.submitList(communityViewModel.allJobPostingList.value?.data?.frontend)}
            "Android" -> {jobPostingAdapter.submitList(communityViewModel.allJobPostingList.value?.data?.android)}
            "IOS" -> {jobPostingAdapter.submitList(communityViewModel.allJobPostingList.value?.data?.ios)}
            "DataScience" -> {jobPostingAdapter.submitList(communityViewModel.allJobPostingList.value?.data?.dataScience)}
            "DataAnalysis" -> {jobPostingAdapter.submitList(communityViewModel.allJobPostingList.value?.data?.dataAnalysis)}
            "Algorithm" -> {jobPostingAdapter.submitList(communityViewModel.allJobPostingList.value?.data?.algorithm)}
            "DataStructure" -> {jobPostingAdapter.submitList(communityViewModel.allJobPostingList.value?.data?.dataStructure)}
            "Network" -> {jobPostingAdapter.submitList(communityViewModel.allJobPostingList.value?.data?.network)}
            "OperatingSystem" -> {jobPostingAdapter.submitList(communityViewModel.allJobPostingList.value?.data?.operatingSystem)}
        }
    }

    private fun initSortView() {
        if (communityViewModel.sortData == "id,DESC") {
            setNewOrderTextView()
        } else {
            setViewOrderTextView()
        }
    }

    private fun initSearchEdittext() {
        binding.searchEdittext.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val keyword = binding.searchEdittext.text.toString()

                if (keyword.isNotBlank()) {
                    getSearchList(communityViewModel.categoryData, keyword, techStack, communityViewModel.sortData)
                } else {
                    getDataList(communityViewModel.categoryData, techStack, communityViewModel.sortData)
                }

                hideKeyBoard()
            }
            true
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
            "채용공고" -> communityViewModel.getSearchJobPosting(techStack, keyword, 0, 10, sort)
            "채용후기" -> communityViewModel.getSearchJobReview(techStack, keyword, 0, 10, sort)
            "스터디" -> communityViewModel.getSearchStudy(techStack, keyword, 0, 10, sort)
            "질문" -> communityViewModel.getSearchQna(techStack, keyword, 0, 10, sort)
        }
    }

    private fun hideKeyBoard() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.searchEdittext.windowToken, 0)
    }

    private fun buttonClick() = with(binding) {
        neworderTextview.setOnClickListener {
            setNewOrderTextView()
            communityViewModel.setOrder("id,DESC")
            callAPI()
        }

        vieworderTextview.setOnClickListener {
            setViewOrderTextView()
            communityViewModel.setOrder("viewCount,DESC")
            callAPI()
        }

        writeButton.setOnClickListener {
            startActivity(getIntentByCategory())
        }
    }

    private fun setNewOrderTextView() = with(binding) {
        neworderTextview.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        vieworderTextview.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
    }

    private fun setViewOrderTextView() = with(binding) {
        neworderTextview.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
        vieworderTextview.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
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

    private fun initJobPostingAdapter() = with(binding) {
        jobPostingAdapter = JobPostingAdapter(true)
        subCommunityRecyclerview.apply {
            adapter = jobPostingAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        jobPostingAdapter.onItemClick = {
            val intent = Intent(requireContext(), DetailJobPostingActivity::class.java)
            intent.putExtra("jobPostingItem", it)
            startActivity(intent)
        }
    }

    private fun initJobReviewAdapter() = with(binding) {
        jobReviewAdapter = JobReviewAdapter(true)
        subCommunityRecyclerview.apply {
            adapter = jobReviewAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        jobReviewAdapter.onItemClick = {
            val intent = Intent(requireContext(), DetailJobReviewActivity::class.java)
            intent.putExtra("jobReviewItem", it)
            startActivity(intent)
        }
    }

    private fun initStudyAdapter() = with(binding) {
        studyAdapter = StudyAdapter(true)
        subCommunityRecyclerview.apply {
            adapter = studyAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        studyAdapter.onItemClick = {
            val intent = Intent(requireContext(), DetailStudyActivity::class.java)
            intent.putExtra("studyItem", it)
            startActivity(intent)
        }
    }

    private fun initQuestionAdapter() = with(binding) {
        questionAdapter = QuestionAdapter(true)
        subCommunityRecyclerview.apply {
            adapter = questionAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        questionAdapter.onItemClick = {
            val intent = Intent(requireContext(), DetailQuestionActivity::class.java)
            intent.putExtra("questionItem", it)
            startActivity(intent)
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
                    jobPostingAdapter.submitList(jobPostingListTechStack)
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
                    jobReviewAdapter.submitList(jobReviewListTechStack)
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
                    studyAdapter.submitList(studyListTechStack)
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
                    questionAdapter.submitList(questionListTechStack)
                }
                is Resource.Error -> {
                    progressCircular.hide()
                    Toast.makeText(requireContext(), "질문 게시글을 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initWriteButtonView() {
        if (!checkUserRole() && communityViewModel.categoryData == "채용공고") {
            hideWriteButton()
        } else {
            showWriteButton()
        }
    }

    private fun checkUserRole(): Boolean =
        getUserRoleFromDataStore() == "MANAGER"

    private fun showWriteButton() {
        binding.writeButton.visibility = View.VISIBLE
    }

    private fun hideWriteButton() {
        binding.writeButton.visibility = View.GONE
    }

    private fun callAPI() {
        val keyword = binding.searchEdittext.text.toString()

        if (keyword.isBlank()) {
            getDataList(communityViewModel.categoryData, techStack, communityViewModel.sortData)
        } else {
            getSearchList(communityViewModel.categoryData, keyword, techStack, communityViewModel.sortData)
        }
    }

}