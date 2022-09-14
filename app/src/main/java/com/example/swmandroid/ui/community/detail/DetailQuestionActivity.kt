package com.example.swmandroid.ui.community.detail

import android.os.Bundle
import android.widget.Toast
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityDetailQuestionBinding
import com.example.swmandroid.model.community.delete.DeleteItemInfo
import com.example.swmandroid.model.community.question.QuestionItem
import com.example.swmandroid.ui.community.CommunityViewModel
import com.example.swmandroid.util.getEmailFromDataStore
import com.example.swmandroid.util.hideMyContentLayout
import com.example.swmandroid.util.showMyContentLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailQuestionActivity : BaseActivity<ActivityDetailQuestionBinding>({ ActivityDetailQuestionBinding.inflate(it) }) {

    private val communityViewModel: CommunityViewModel by viewModel()

    private var questionItem: QuestionItem? = null

    private var deleteDialog : MaterialAlertDialogBuilder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initMyContentLayout()
        buttonClick()
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
    }

    private fun getDeleteItemInfo(): DeleteItemInfo =
        DeleteItemInfo(
            questionItem?.id ?: 0,
            getEmailFromDataStore(),
        )

    private fun showDeleteDialog(){
        deleteDialog = MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.delete))
            .setMessage(resources.getString(R.string.dialog_delete_title))
            .setNeutralButton(resources.getString(R.string.cancel)){dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.ok)){_, _ ->
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