package com.example.swmandroid.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentFullCommunityBinding
import com.example.swmandroid.model.community.JobReviewItem
import com.example.swmandroid.model.community.QuestionItem
import com.example.swmandroid.model.community.StudyItem
import com.example.swmandroid.ui.community.adapter.JobReviewAdapter
import com.example.swmandroid.ui.community.adapter.QuestionAdapter
import com.example.swmandroid.ui.community.adapter.StudyAdapter

class FullCommunityFragment : BaseFragment<FragmentFullCommunityBinding>() {

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFullCommunityBinding {
        return FragmentFullCommunityBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        connectJobReviewAdapter()
        connectStudyAdapter()
        connectQuestionAdapter()
    }

    fun moveSubCommunityFragment(category: String) {
        val action = FullCommunityFragmentDirections.actionFullCommunityFragmentToSubCommunityFragment(category)
        binding.root.findNavController().navigate(action)
    }

    private fun connectJobReviewAdapter() = with(binding) {
        val adapter = JobReviewAdapter(getJobReviewItem())

        jobreviewRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        jobreviewRecyclerview.adapter = adapter
    }

    private fun connectStudyAdapter() = with(binding) {
        val adapter = StudyAdapter(getStudyItem())

        studyRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        studyRecyclerview.adapter = adapter
    }

    private fun connectQuestionAdapter() = with(binding) {
        val adapter = QuestionAdapter(getQuestionItem())

        questionRecyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        questionRecyclerview.adapter = adapter
    }

    private fun getJobReviewItem(): List<JobReviewItem> {
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

            )
    }

    private fun getStudyItem(): List<StudyItem> {
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
        )
    }

    private fun getQuestionItem(): List<QuestionItem> {
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
        )
    }

}