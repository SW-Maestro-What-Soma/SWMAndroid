package com.example.swmandroid.ui.community.post

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityPostJobreviewBinding
import com.example.swmandroid.model.community.jobreview.JobReviewItem
import com.example.swmandroid.ui.community.CommunityViewModel
import com.example.swmandroid.util.Resource
import com.example.swmandroid.util.getCurrentTime
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostJobReviewActivity : BaseActivity<ActivityPostJobreviewBinding>({ ActivityPostJobreviewBinding.inflate(it) }) {

    private val communityViewModel: CommunityViewModel by viewModel()

    private var techStackDialog: MaterialAlertDialogBuilder? = null
    private var passFailDialog: MaterialAlertDialogBuilder? = null
    private var processDialog: MaterialAlertDialogBuilder? = null

    private val techStackItems = arrayOf("Backend", "Frontend", "Android", "IOS", "DataScience", "DataAnalysis")
    private val passFailItems = arrayOf("합격", "불합격")
    private val processItems = arrayOf("코딩테스트", "1차 면접", "2차 면접")

    private var selectedTechStack = "Backend"
    private var selectedPassFail = "합격"
    private var selectedProcess = "코딩테스트"

    private val checkedItem = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideProgressCircular()
        buttonClick()
    }

    private fun hideProgressCircular() {
        binding.progressCircular.hide()
    }

    private fun showProgressCircular() {
        binding.progressCircular.show()
    }

    private fun buttonClick() = with(binding) {
        techAlertTextview.setOnClickListener {
            showTechStackDialog()
        }

        passFailAlertTextview.setOnClickListener {
            showPassFailDialog()
        }

        processAlertTextview.setOnClickListener {
            showProcessDialog()
        }

        writeButton.setOnClickListener {
            if (checkAllContentInput()) {
                postJobReview()
            } else {
                Toast.makeText(this@PostJobReviewActivity, "모든 내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
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
            .setSingleChoiceItems(techStackItems, checkedItem) { _, which ->
                selectedTechStack = techStackItems[which]
            }
        techStackDialog?.show()
    }

    private fun showPassFailDialog() {
        passFailDialog = MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.pass_fail_choice_text))
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                binding.passFailAlertTextview.text = selectedPassFail
            }
            .setSingleChoiceItems(passFailItems, checkedItem) { _, which ->
                selectedPassFail = passFailItems[which]
            }
        passFailDialog?.show()
    }

    private fun showProcessDialog() {
        processDialog = MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.process_choice_text))
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                binding.processAlertTextview.text = selectedProcess
            }
            .setSingleChoiceItems(processItems, checkedItem) { _, which ->
                selectedProcess = processItems[which]
            }
        processDialog?.show()
    }

    private fun checkAllContentInput(): Boolean {
        with(binding) {
            return (titleEdittext.text.isNotBlank()
                    && techAlertTextview.text != resources.getString(R.string.tech_drop_text)
                    && passFailAlertTextview.text != resources.getString(R.string.pass_fail_choice_text)
                    && processAlertTextview.text != resources.getString(R.string.process_choice_text)
                    && contentEdittext.text.isNotBlank())
        }
    }

    private fun postJobReview() = with(binding) {
        val addItem = getJobReviewItem()

        communityViewModel.postJobReview(addItem)
        communityViewModel.statusPostJobReview.observe(this@PostJobReviewActivity) { status ->
            when (status) {
                is Resource.Loading -> {
                    showProgressCircular()
                }
                is Resource.Success -> {
                    hideProgressCircular()
                    finish()
                }
                is Resource.Error -> {
                    hideProgressCircular()
                    Toast.makeText(this@PostJobReviewActivity, "채용후기를 등록하는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getJobReviewItem(): JobReviewItem =
        JobReviewItem(
            title = binding.titleEdittext.text.toString(),
            techStack = binding.techAlertTextview.text.toString(),
            passFail = true,
            processCategory = binding.processAlertTextview.text.toString(),
            text = binding.contentEdittext.text.toString(),
            commentCount = 0,
            id = 0,
            userId = 0,
            viewCount = 0,
            createdAt = getCurrentTime(),
        )

    override fun onDestroy() {
        super.onDestroy()
        techStackDialog = null
        passFailDialog = null
        processDialog = null
    }

}