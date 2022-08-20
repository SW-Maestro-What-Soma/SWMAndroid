package com.example.swmandroid.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentTechCommunityBinding
import com.example.swmandroid.model.community.JobPostingItem
import com.example.swmandroid.model.community.JobReviewItem
import com.example.swmandroid.model.community.QuestionItem
import com.example.swmandroid.model.community.StudyItem
import com.example.swmandroid.ui.community.adapter.JobPostingAdapter
import com.example.swmandroid.ui.community.adapter.JobReviewAdapter
import com.example.swmandroid.ui.community.adapter.QuestionAdapter
import com.example.swmandroid.ui.community.adapter.StudyAdapter

class TechCommunityFragment : BaseFragment<FragmentTechCommunityBinding>() {

    companion object {
        private const val KEY_TECH_COMMUNITY = "tech_stack"
        private const val CATEGORY = "category"

        @JvmStatic
        fun newInstance(techStack: String, category : String) =
            TechCommunityFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TECH_COMMUNITY, techStack)
                    putString(CATEGORY, category)
                }
            }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTechCommunityBinding {
        return FragmentTechCommunityBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       initView()
    }

    private fun initView(){
        val category = arguments?.getString(CATEGORY)

        when(category){
            "채용공고" -> connectJobPostingAdapter()
            "채용후기" -> connectJobReviewAdapter()
            "스터디" -> connectStudyAdapter()
            "질문" -> connectQuestionAdapter()
        }
    }

    private fun connectJobPostingAdapter() = with(binding){
        val adapter = JobPostingAdapter(getJobPostingItem())

        subCommunityRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        subCommunityRecyclerview.adapter = adapter
    }

    private fun connectJobReviewAdapter() = with(binding) {
        val adapter = JobReviewAdapter(getJobReviewItem())

        subCommunityRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        subCommunityRecyclerview.adapter = adapter
    }

    private fun connectStudyAdapter() = with(binding) {
        val adapter = StudyAdapter(getStudyItem())

        subCommunityRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        subCommunityRecyclerview.adapter = adapter
    }

    private fun connectQuestionAdapter() = with(binding) {
        val adapter = QuestionAdapter(getQuestionItem())

        subCommunityRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        subCommunityRecyclerview.adapter = adapter
    }

    private fun getJobPostingItem() : List<JobPostingItem> {
        return listOf(
            JobPostingItem(
                "카카오엔터프라이즈",
                "Android",
                "06/23 ~ 07/23",
                "22.07.23",
                32,
            ),
            JobPostingItem(
                "카카오엔터프라이즈",
                "Android",
                "06/23 ~ 07/23",
                "22.07.23",
                32,
            ),
            JobPostingItem(
                "카카오엔터프라이즈",
                "Android",
                "06/23 ~ 07/23",
                "22.07.23",
                32,
            ),
            JobPostingItem(
                "카카오엔터프라이즈",
                "Android",
                "06/23 ~ 07/23",
                "22.07.23",
                32,
            ),
            JobPostingItem(
                "카카오엔터프라이즈",
                "Android",
                "06/23 ~ 07/23",
                "22.07.23",
                32,
            ),
        )
    }

    private fun getJobReviewItem() : List<JobReviewItem> {
        return listOf(
            JobReviewItem(
                "카카오엔터프라이즈",
                "Android",
                "코딩테스트",
                "김시진",
                "실버 5",
                "22.07.23",
                32
            ),
            JobReviewItem(
                "카카오엔터프라이즈",
                "Android",
                "코딩테스트",
                "김시진",
                "실버 5",
                "22.07.23",
                32
            ),
            JobReviewItem(
                "카카오엔터프라이즈",
                "Android",
                "코딩테스트",
                "김시진",
                "실버 5",
                "22.07.23",
                32
            ),
            JobReviewItem(
                "카카오엔터프라이즈",
                "Android",
                "코딩테스트",
                "김시진",
                "실버 5",
                "22.07.23",
                32
            ),
            JobReviewItem(
                "카카오엔터프라이즈",
                "Android",
                "코딩테스트",
                "김시진",
                "실버 5",
                "22.07.23",
                32
            ),
            JobReviewItem(
                "카카오엔터프라이즈",
                "Android",
                "코딩테스트",
                "김시진",
                "실버 5",
                "22.07.23",
                32
            ),
            JobReviewItem(
                "카카오엔터프라이즈",
                "Android",
                "코딩테스트",
                "김시진",
                "실버 5",
                "22.07.23",
                32
            ),
        )
    }

    private fun getStudyItem() : List<StudyItem> {
        return listOf(
            StudyItem(
                "안드로이드 토이 프로젝트 모집합니다.",
                "안드로이드",
                "주2회",
                "월,목",
                "온라인",
                "김시진",
                "실버 5",
                "실버 3",
                "골드 3",
                "22.07.23",
                32
            ),
            StudyItem(
                "안드로이드 토이 프로젝트 모집합니다.",
                "안드로이드",
                "주2회",
                "월,목",
                "온라인",
                "김시진",
                "실버 5",
                "실버 3",
                "골드 3",
                "22.07.23",
                32
            ),
            StudyItem(
                "안드로이드 토이 프로젝트 모집합니다.",
                "안드로이드",
                "주2회",
                "월,목",
                "온라인",
                "김시진",
                "실버 5",
                "실버 3",
                "골드 3",
                "22.07.23",
                32
            ),
            StudyItem(
                "안드로이드 토이 프로젝트 모집합니다.",
                "안드로이드",
                "주2회",
                "월,목",
                "온라인",
                "김시진",
                "실버 5",
                "실버 3",
                "골드 3",
                "22.07.23",
                32
            ),
            StudyItem(
                "안드로이드 토이 프로젝트 모집합니다.",
                "안드로이드",
                "주2회",
                "월,목",
                "온라인",
                "김시진",
                "실버 5",
                "실버 3",
                "골드 3",
                "22.07.23",
                32
            ),
            StudyItem(
                "안드로이드 토이 프로젝트 모집합니다.",
                "안드로이드",
                "주2회",
                "월,목",
                "온라인",
                "김시진",
                "실버 5",
                "실버 3",
                "골드 3",
                "22.07.23",
                32
            ),
            StudyItem(
                "안드로이드 토이 프로젝트 모집합니다.",
                "안드로이드",
                "주2회",
                "월,목",
                "온라인",
                "김시진",
                "실버 5",
                "실버 3",
                "골드 3",
                "22.07.23",
                32
            ),
            StudyItem(
                "안드로이드 토이 프로젝트 모집합니다.",
                "안드로이드",
                "주2회",
                "월,목",
                "온라인",
                "김시진",
                "실버 5",
                "실버 3",
                "골드 3",
                "22.07.23",
                32
            ),
            StudyItem(
                "안드로이드 토이 프로젝트 모집합니다.",
                "안드로이드",
                "주2회",
                "월,목",
                "온라인",
                "김시진",
                "실버 5",
                "실버 3",
                "골드 3",
                "22.07.23",
                32
            ),
        )
    }

    private fun getQuestionItem() : List<QuestionItem> {
        return listOf(
            QuestionItem(
                "안드로이드 질문입니다.",
                "김시진",
                "실버 5",
                "22.07.23",
                32,
            ),
            QuestionItem(
                "안드로이드 질문입니다.",
                "김시진",
                "실버 5",
                "22.07.23",
                32,
            ),
            QuestionItem(
                "안드로이드 질문입니다.",
                "김시진",
                "실버 5",
                "22.07.23",
                32,
            ),
            QuestionItem(
                "안드로이드 질문입니다.",
                "김시진",
                "실버 5",
                "22.07.23",
                32,
            ),
            QuestionItem(
                "안드로이드 질문입니다.",
                "김시진",
                "실버 5",
                "22.07.23",
                32,
            ),
            QuestionItem(
                "안드로이드 질문입니다.",
                "김시진",
                "실버 5",
                "22.07.23",
                32,
            ),
            QuestionItem(
                "안드로이드 질문입니다.",
                "김시진",
                "실버 5",
                "22.07.23",
                32,
            ),
            QuestionItem(
                "안드로이드 질문입니다.",
                "김시진",
                "실버 5",
                "22.07.23",
                32,
            ),
            QuestionItem(
                "안드로이드 질문입니다.",
                "김시진",
                "실버 5",
                "22.07.23",
                32,
            ),
        )
    }
}