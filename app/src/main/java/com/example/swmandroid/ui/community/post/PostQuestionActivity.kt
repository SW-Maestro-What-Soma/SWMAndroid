package com.example.swmandroid.ui.community.post

import android.os.Bundle
import android.widget.Toast
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityPostQuestionBinding
import com.example.swmandroid.model.community.question.QuestionItem
import com.example.swmandroid.ui.community.CommunityViewModel
import com.example.swmandroid.util.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostQuestionActivity : BaseActivity<ActivityPostQuestionBinding>({ ActivityPostQuestionBinding.inflate(it) }) {

    private val communityViewModel: CommunityViewModel by viewModel()

    private var techStackDialog: MaterialAlertDialogBuilder? = null

    private val techStackItems = arrayOf("Backend", "Frontend", "Android", "IOS", "DataScience", "DataAnalysis")

    private var selectedTechStack = "Backend"

    private var checkedTechStackItemIdx = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideProgressCircular(binding.progressCircular)
        buttonClick()
    }

    private fun buttonClick() = with(binding) {
        techAlertTextview.setOnClickListener {
            showTechStackDialog()
        }

        writeButton.setOnClickListener {
            if (checkAllContent()) {
                postQuestion()
            } else {
                Toast.makeText(this@PostQuestionActivity, "모든 내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showTechStackDialog() {
        techStackDialog = MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.tech_drop_text))
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                binding.techAlertTextview.text = selectedTechStack
            }
            .setSingleChoiceItems(techStackItems, checkedTechStackItemIdx) { _, which ->
                selectedTechStack = techStackItems[which]
                checkedTechStackItemIdx = which
            }
        techStackDialog?.show()
    }

    private fun checkAllContent(): Boolean {
        with(binding) {
            return (titleEdittext.text.isNotBlank()
                    && techAlertTextview.text != resources.getString(R.string.tech_drop_text)
                    && contentEdittext.text.isNotBlank()
                    )
        }
    }

    private fun postQuestion() = with(binding) {
        val addItem = getPostItem()

        communityViewModel.postQuestion(addItem)
        communityViewModel.statusPostQuestion.observe(this@PostQuestionActivity) { status ->
            when (status) {
                is Resource.Loading -> {
                    showProgressCircular(progressCircular)
                }
                is Resource.Success -> {
                    hideProgressCircular(progressCircular)
                    finish()
                }
                is Resource.Error -> {
                    hideProgressCircular(progressCircular)
                    Toast.makeText(this@PostQuestionActivity, "질문 게시글을 등록하는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getPostItem(): QuestionItem =
        QuestionItem(
            title = binding.titleEdittext.text.toString(),
            techStack = binding.techAlertTextview.text.toString(),
            text = binding.contentEdittext.text.toString(),
            createdAt = getCurrentTime(),
            commentCount = 0,
            fkUserId = 0,
            id = 0,
            viewCount = 0,
            voteCount = 0,
            userEmail = getEmailFromDataStore()
        )

    override fun onDestroy() {
        super.onDestroy()
        techStackDialog = null
    }
}