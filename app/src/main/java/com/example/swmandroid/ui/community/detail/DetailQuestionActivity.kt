package com.example.swmandroid.ui.community.detail

import android.os.Bundle
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityDetailQuestionBinding
import com.example.swmandroid.model.community.question.QuestionItem

class DetailQuestionActivity : BaseActivity<ActivityDetailQuestionBinding>({ ActivityDetailQuestionBinding.inflate(it) }) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        val data = intent.getParcelableExtra<QuestionItem>("Question")

        questionTitle.text = data?.title
        questionCategory.text = data?.techStack
        questionViewcount.text = data?.viewCount.toString()
        questionCommentcount.text = data?.commentCount.toString()
        questionCreatedat.text = data?.createdAt
        voteCount.text = getString(R.string.vote_count_text, data?.voteCount)
        questionContent.text = data?.text
    }
}