package com.example.swmandroid.ui.community.detail

import android.os.Bundle
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityDetailStudyBinding
import com.example.swmandroid.model.community.study.StudyItem

class DetailStudyActivity : BaseActivity<ActivityDetailStudyBinding>({ ActivityDetailStudyBinding.inflate(it) }) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        val data = intent.getParcelableExtra<StudyItem>("Study")

        studyTitle.text = data?.title
        studyCategory.text = data?.techStack
        studyPerweek.text = getString(R.string.per_week_text, data?.perWeek)
        studyDayofweek.text = data?.dayOfTheWeek
        studyOnoff.text = if (data?.onOffline == true) "온라인" else "오프라인"
        studyMintier.text = data?.minGrade
        studyMaxtier.text = data?.maxGrade
        studyViewcount.text = data?.viewCount.toString()
        studyCommentcount.text = data?.commentCount.toString()
        studyCreatedat.text = data?.createdAt
        studyLink.text = data?.meetingLink
        studyContent.text = data?.text

        //TODO 닉네임 변경
    }
}