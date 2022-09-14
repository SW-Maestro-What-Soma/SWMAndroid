package com.example.swmandroid.ui.community.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityDetailQuestionBinding
import com.example.swmandroid.model.community.delete.DeleteItemInfo
import com.example.swmandroid.model.community.question.QuestionItem
import com.example.swmandroid.ui.community.CommunityViewModel
import com.example.swmandroid.ui.community.post.PostQuestionActivity
import com.example.swmandroid.util.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailQuestionActivity : BaseActivity<ActivityDetailQuestionBinding>({ ActivityDetailQuestionBinding.inflate(it) }) {

    private val communityViewModel: CommunityViewModel by viewModel()

    private var deleteDialog: MaterialAlertDialogBuilder? = null

    private var questionItem: QuestionItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initQuestionItem()
        initView()
        initMyContentLayout()
        buttonClick()
    }

    override fun onResume() {
        super.onResume()

        communityViewModel.getQna(questionItem?.id ?: -1)
    }

    private fun initQuestionItem() {
        questionItem = intent.getParcelableExtra("questionItem")
    }

    private fun initView() = with(binding) {
        communityViewModel.question.observe(this@DetailQuestionActivity) { questionResponse ->
            when (questionResponse) {
                is Resource.Loading -> {
                    showProgressCircular(progressCircular)
                }

                is Resource.Success -> {
                    hideProgressCircular(progressCircular)

                    questionTitle.text = questionResponse.data?.title
                    questionCategory.text = questionResponse.data?.techStack
                    questionViewcount.text = questionResponse.data?.viewCount.toString()
                    questionCommentcount.text = questionResponse.data?.commentCount.toString()
                    questionCreatedat.text = questionResponse.data?.createdAt
                    voteCount.text = getString(R.string.vote_count_text, questionResponse.data?.voteCount)
                    questionContent.text = questionResponse.data?.text

                    //TODO 닉네임 연결해야함
                }

                is Resource.Error -> {
                    hideProgressCircular(progressCircular)
                }
            }
        }
    }

    private fun initMyContentLayout() = with(binding) {
        if (checkUserEmail()) {
            showMyContentLayout(myContentLayout)
        } else {
            hideMyContentLayout(myContentLayout)
        }
    }

    private fun checkUserEmail(): Boolean =
        questionItem?.userEmail == getEmailFromDataStore()

    private fun buttonClick() = with(binding) {
        questionDeleteButton.setOnClickListener {
            showDeleteDialog()
        }

        questionModifyButton.setOnClickListener{
            val intent = Intent(this@DetailQuestionActivity, PostQuestionActivity::class.java)
            intent.putExtra("modify", questionItem)
            startActivity(intent)
        }
    }

    private fun getDeleteItemInfo(): DeleteItemInfo =
        DeleteItemInfo(
            questionItem?.id ?: -1,
            getEmailFromDataStore(),
        )

    private fun showDeleteDialog() {
        deleteDialog = MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.delete))
            .setMessage(resources.getString(R.string.dialog_delete_title))
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                communityViewModel.deleteQnaPost(getDeleteItemInfo())
                communityViewModel.statusDeleteQnaPost.observe(this@DetailQuestionActivity) { deleteResponse ->
                    if (deleteResponse.code() == 200) {
                        Toast.makeText(this@DetailQuestionActivity, "삭제 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@DetailQuestionActivity, "삭제를 실패하셨습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        deleteDialog?.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        deleteDialog = null
    }

}