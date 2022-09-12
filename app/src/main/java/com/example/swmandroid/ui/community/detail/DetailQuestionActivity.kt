package com.example.swmandroid.ui.community.detail

import android.os.Bundle
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityDetailQuestionBinding
import com.example.swmandroid.model.community.question.QuestionItem
import com.example.swmandroid.util.getEmailFromDataStore
import com.example.swmandroid.util.hideMyContentLayout
import com.example.swmandroid.util.showMyContentLayout

class DetailQuestionActivity : BaseActivity<ActivityDetailQuestionBinding>({ ActivityDetailQuestionBinding.inflate(it) }) {

    private var questionItem : QuestionItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initMyContentLayout()
    }

    private fun initView() = with(binding) {
        questionItem = intent.getParcelableExtra("Question")

        questionTitle.text = questionItem?.title
        questionCategory.text = questionItem?.techStack
        questionViewcount.text = questionItem?.viewCount.toString()
        questionCommentcount.text = questionItem?.commentCount.toString()
        questionCreatedat.text = questionItem?.createdAt
        voteCount.text = getString(R.string.vote_count_text, questionItem?.voteCount)
        questionContent.text = questionItem?.text
    }

    private fun initMyContentLayout() =with(binding){
        if(checkUserEmail()){
            showMyContentLayout(myContentLayout)
        }else{
            hideMyContentLayout(myContentLayout)
        }
    }

    private fun checkUserEmail(): Boolean =
        questionItem?.userEmail == getEmailFromDataStore()


}