package com.example.swmandroid.ui.community.detail

import android.os.Bundle
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityDetailJobpostingBinding
import com.example.swmandroid.model.community.jobposting.JobPostingItem

class DetailJobPostingActivity : BaseActivity<ActivityDetailJobpostingBinding>({ ActivityDetailJobpostingBinding.inflate(it) }) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        val data = intent.getParcelableExtra<JobPostingItem>("JobPosting")

        jobpostingTitle.text = data?.title
        jobpostingCategory.text = data?.techStack
        jobpostingCareer.text = data?.career
        jobpostingViewcount.text = data?.viewCount.toString()
        jobpostingCreatedat.text = data?.createdAt
        jobpostingLink.text = data?.incruitLink
        jobpostingStartEndTime.text = data?.startEndTime
        jobpostingContent.text = data?.text
        //TODO 닉네임 연결해야함
    }

}