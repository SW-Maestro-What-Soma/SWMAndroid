package com.example.swmandroid.ui.community.detail

import android.os.Bundle
import android.view.View
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityDetailJobreviewBinding
import com.example.swmandroid.model.community.jobreview.JobReviewItem
import com.example.swmandroid.util.getEmailFromDataStore
import com.example.swmandroid.util.hideMyContentLayout
import com.example.swmandroid.util.showMyContentLayout

class DetailJobReviewActivity : BaseActivity<ActivityDetailJobreviewBinding>({ ActivityDetailJobreviewBinding.inflate(it) }) {

    private var jobReviewItem: JobReviewItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initMyContentLayout()
    }

    private fun initView() = with(binding) {
        jobReviewItem = intent.getParcelableExtra("JobReview")

        jobreviewTitle.text = jobReviewItem?.title
        jobreviewCategory.text = jobReviewItem?.techStack
        jobreviewProgress.text = jobReviewItem?.processCategory
        jobreviewViewcount.text = jobReviewItem?.viewCount.toString()
        jobreviewCommentcount.text = jobReviewItem?.commentCount.toString()
        jobreviewCreatedat.text = jobReviewItem?.createdAt
        jobreviewContent.text = jobReviewItem?.text

        //TODO 닉네임 연결해야함
        //TODO 댓글 연결해야함
    }

    private fun initMyContentLayout() = with(binding) {
        if(checkUserEmail()){
            showMyContentLayout(myContentLayout)
        }else{
            hideMyContentLayout(myContentLayout)
        }
    }

    private fun checkUserEmail(): Boolean =
        jobReviewItem?.userEmail == getEmailFromDataStore()

}