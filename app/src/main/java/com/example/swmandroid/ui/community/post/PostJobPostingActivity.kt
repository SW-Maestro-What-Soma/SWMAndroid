package com.example.swmandroid.ui.community.post

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.core.util.Pair
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityPostJobpostingBinding
import com.example.swmandroid.model.community.jobposting.JobPostingItem
import com.example.swmandroid.ui.community.CommunityViewModel
import com.example.swmandroid.util.Resource
import com.example.swmandroid.util.getCurrentTime
import com.example.swmandroid.util.hideProgressCircular
import com.example.swmandroid.util.showProgressCircular
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class PostJobPostingActivity : BaseActivity<ActivityPostJobpostingBinding>({ ActivityPostJobpostingBinding.inflate(it) }) {

    private val langArray = arrayOf("Backend", "Frontend", "Android", "IOS", "DataScience", "DataAnalysis")
    private val selectedLanguage = BooleanArray(langArray.size)
    private val langList = mutableListOf<Int>()

    private val communityViewModel: CommunityViewModel by viewModel()

    private var alertDialog: AlertDialog? = null
    private var datePicker: MaterialDatePicker<Pair<Long, Long>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideProgressCircular(binding.progressCircular)
        buttonClick()
    }

    private fun buttonClick() = with(binding) {
        techAlertTextview.setOnClickListener {
            showTechStackDialog()
        }

        datePickerTextview.setOnClickListener {
            showDatePicker()
        }

        writeButton.setOnClickListener {
            if (checkAllContentInput()) {
                postJobPosting()
            } else {
                Toast.makeText(this@PostJobPostingActivity, "모든 내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showTechStackDialog() = with(binding) {
        alertDialog = AlertDialog.Builder(this@PostJobPostingActivity)
            .setTitle(resources.getString(R.string.tech_drop_text))
            .setCancelable(false)
            .setMultiChoiceItems(langArray, selectedLanguage) { _, which, isChecked ->
                if (isChecked) {
                    langList.add(which)
                    langList.sort()
                } else {
                    langList.remove(which)
                }
            }
            .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                val stringBuilder = StringBuilder()

                for (i in 0 until langList.size) {
                    stringBuilder.append(langArray[langList[i]])

                    if (i != langList.size - 1) {
                        stringBuilder.append(", ")
                    }
                }

                if (stringBuilder.isBlank()) {
                    techAlertTextview.text = resources.getString(R.string.tech_drop_text)
                } else {
                    techAlertTextview.text = stringBuilder
                }
            }
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setNeutralButton(resources.getString(R.string.reset)) { _, _ ->
                for (i in selectedLanguage.indices) {
                    selectedLanguage[i] = false
                    langList.clear()

                }

                techAlertTextview.text = resources.getString(R.string.tech_drop_text)
            }
            .show()
    }

    private fun showDatePicker() = with(binding) {
        datePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("채용공고 날짜범위를 선택해주세요.")
            .build()

        datePicker?.apply {
            show(supportFragmentManager, datePicker.toString())
            addOnPositiveButtonClickListener {
                val startDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(it.first)
                val endDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(it.second)
                datePickerTextview.text = getString(R.string.start_end_date_text, "$startDate ~ $endDate")
            }
            addOnNegativeButtonClickListener { dismiss() }
        }
    }

    private fun checkAllContentInput(): Boolean {
        with(binding) {
            return (titleEdittext.text.isNotBlank()
                    && techAlertTextview.text != resources.getString(R.string.tech_drop_text)
                    && linkEdittext.text.isNotBlank()
                    && careerEdittext.text.isNotBlank()
                    && datePickerTextview.text != resources.getString(R.string.job_posting_date_input)
                    && contentEdittext.text.isNotBlank())
        }
    }

    private fun postJobPosting() = with(binding) {
        val addItem = getJobPostingItem()

        communityViewModel.postJobPosting(addItem)
        communityViewModel.statusPostJobPosting.observe(this@PostJobPostingActivity) { status ->
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
                    Toast.makeText(this@PostJobPostingActivity, "채용공고를 등록하는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getJobPostingItem(): JobPostingItem =
        JobPostingItem(
            title = binding.titleEdittext.text.toString(),
            techStack = binding.techAlertTextview.text.toString(),
            incruitLink = binding.linkEdittext.text.toString(),
            career = binding.careerEdittext.text.toString(),
            startEndTime = binding.datePickerTextview.text.toString(),
            text = binding.contentEdittext.text.toString(),
            createdAt = getCurrentTime(),
            id = 0,
            viewCount = 0,
        )

    override fun onDestroy() {
        super.onDestroy()
        alertDialog = null
        datePicker = null
    }
}