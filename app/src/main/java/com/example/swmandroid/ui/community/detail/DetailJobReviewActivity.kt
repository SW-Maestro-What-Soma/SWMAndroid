package com.example.swmandroid.ui.community.detail

import android.os.Bundle
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityDetailJobreviewBinding
import com.example.swmandroid.model.community.jobreview.JobReviewItem

class DetailJobReviewActivity : BaseActivity<ActivityDetailJobreviewBinding>({ActivityDetailJobreviewBinding.inflate(it)}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() = with(binding){
        val data = intent.getParcelableExtra<JobReviewItem>("JobReview")

        jobreviewTitle.text = data?.title
        jobreviewCategory.text = data?.techStack
        jobreviewProgress.text = data?.processCategory
        jobreviewViewcount.text = data?.viewCount.toString()
        jobreviewCommentcount.text = data?.commentCount.toString()
        jobreviewCreatedat.text = data?.createdAt
        jobreviewContent.text = data?.text

        //TODO 닉네임 연결해야함
        //TODO 댓글 연결해야함
    }



}