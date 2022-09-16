package com.example.swmandroid.ui.community.post

import android.os.Bundle
import android.widget.Toast
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityPostJobreviewBinding
import com.example.swmandroid.model.community.jobreview.JobReviewItem
import com.example.swmandroid.model.community.update.UpdateJobReviewItem
import com.example.swmandroid.ui.community.CommunityViewModel
import com.example.swmandroid.util.*
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

    private var checkedTechStackItemIdx = 0
    private var checkedPassFailItemIdx = 0
    private var checkedProcessItemIdx = 0

    private var jobReviewItem: JobReviewItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getIntentData()
        initWriteButton()
        hideProgressCircular(binding.progressCircular)
        buttonClick()
    }

    private fun getIntentData() {
        jobReviewItem = intent.getParcelableExtra("modify")
    }

    private fun initWriteButton() = with(binding) {
        if (jobReviewItem == null) {
            writeButton.text = resources.getString(R.string.write)
        } else {
            writeButton.text = resources.getString(R.string.modify_button_text)
            initView()
        }
    }

    private fun initView() = with(binding) {
        titleEdittext.setText(jobReviewItem?.title)
        techAlertTextview.text = jobReviewItem?.techStack
        passFailAlertTextview.text = if (jobReviewItem?.passFail == true) "합격" else "불합격"
        processAlertTextview.text = jobReviewItem?.processCategory
        contentEdittext.setText(jobReviewItem?.text)
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
                if (jobReviewItem == null) {
                    postJobReview()
                } else {
                    postUpdateJobReview()
                }
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
            .setSingleChoiceItems(techStackItems, checkedTechStackItemIdx) { _, which ->
                selectedTechStack = techStackItems[which]
                checkedTechStackItemIdx = which
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
            .setSingleChoiceItems(passFailItems, checkedPassFailItemIdx) { _, which ->
                selectedPassFail = passFailItems[which]
                checkedPassFailItemIdx
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
            .setSingleChoiceItems(processItems, checkedProcessItemIdx) { _, which ->
                selectedProcess = processItems[which]
                checkedProcessItemIdx = which
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
                    showProgressCircular(progressCircular)
                }
                is Resource.Success -> {
                    hideProgressCircular(progressCircular)
                    finish()
                }
                is Resource.Error -> {
                    hideProgressCircular(progressCircular)
                    Toast.makeText(this@PostJobReviewActivity, "채용후기를 등록하는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getJobReviewItem(): JobReviewItem =
        JobReviewItem(
            title = binding.titleEdittext.text.toString(),
            techStack = binding.techAlertTextview.text.toString(),
            passFail = binding.passFailAlertTextview.text == "합격",
            processCategory = binding.processAlertTextview.text.toString(),
            text = binding.contentEdittext.text.toString(),
            commentCount = 0,
            id = 0,
            userId = 0,
            viewCount = 0,
            createdAt = getCurrentTime(),
            userEmail = getEmailFromDataStore(),
            voteCount = 0,
        )

    private fun postUpdateJobReview() {
        val addItem = getUpdateJobReviewItem()

        communityViewModel.updateJobReview(addItem)
        communityViewModel.updateJobReviewPost.observe(this@PostJobReviewActivity) { updateResponse ->
            if (updateResponse.code() == 200) {
                Toast.makeText(this@PostJobReviewActivity, "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this@PostJobReviewActivity, "수정이 실패하였습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getUpdateJobReviewItem(): UpdateJobReviewItem =
        UpdateJobReviewItem(
            title = binding.titleEdittext.text.toString(),
            techStack = binding.techAlertTextview.text.toString(),
            processCategory = binding.processAlertTextview.text.toString(),
            id = jobReviewItem?.id ?: -1,
            passFail = binding.passFailAlertTextview.text == "합격",
            userEmail = getEmailFromDataStore(),
            text = binding.contentEdittext.text.toString(),
        )

    override fun onDestroy() {
        super.onDestroy()
        techStackDialog = null
        passFailDialog = null
        processDialog = null
    }

}