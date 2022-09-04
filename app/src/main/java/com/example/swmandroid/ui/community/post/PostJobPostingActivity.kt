package com.example.swmandroid.ui.community.post

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityPostJobpostingBinding
import com.example.swmandroid.model.community.jobposting.JobPostingItem
import com.example.swmandroid.ui.community.CommunityViewModel
import com.example.swmandroid.util.Resource
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class PostJobPostingActivity : BaseActivity<ActivityPostJobpostingBinding>({ ActivityPostJobpostingBinding.inflate(it) }) {

    private val langArray = arrayOf("Backend", "Frontend", "Android", "IOS", "DataScience", "DataAnalysis")
    private lateinit var selectedLanguage: BooleanArray
    private val langList = mutableListOf<Int>()

    private val communityViewModel: CommunityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectedLanguage = BooleanArray(langArray.size)
        binding.progressCircular.hide()

        buttonClick()
    }

    private fun buttonClick() = with(binding) {
        techDropdownTextview.setOnClickListener {
            showTechStackDialog()
        }

        datePickerTextview.setOnClickListener {
            showDatePicker()
        }

        writeButton.setOnClickListener {
            postJobPosting()
        }
    }

    private fun showTechStackDialog() = with(binding) {
        AlertDialog.Builder(this@PostJobPostingActivity)
            .setTitle("기술스택을 고르세요.")
            .setCancelable(false)
            .setMultiChoiceItems(langArray, selectedLanguage) { _, which, isChecked ->
                if (isChecked) {
                    langList.add(which)
                    langList.sort()
                } else {
                    langList.remove(which)
                }
            }
            .setPositiveButton("등록") { _, _ ->
                val stringBuilder = StringBuilder()

                for (i in 0 until langList.size) {
                    stringBuilder.append(langArray[langList[i]])

                    if (i != langList.size - 1) {
                        stringBuilder.append(", ")
                    }
                }

                techDropdownTextview.text = stringBuilder
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }
            .setNeutralButton("초기화") { _, _ ->
                for (i in selectedLanguage.indices) {
                    selectedLanguage[i] = false
                    langList.clear()

                }

                techDropdownTextview.text = "기술선택"
            }
            .show()
    }

    private fun showDatePicker() = with(binding) {
        val datePicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("채용공고 날짜범위를 선택해주세요.")
            .build()

        datePicker.apply {
            show(supportFragmentManager, datePicker.toString())
            addOnPositiveButtonClickListener {
                val startDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(it.first)
                val endDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(it.second)
                datePickerTextview.text = getString(R.string.start_end_date_text, "$startDate ~ $endDate")
            }
            addOnNegativeButtonClickListener { dismiss() }
        }
    }

    private fun postJobPosting() = with(binding) {
        communityViewModel.postJobPosting(getJobPostingItem())
        communityViewModel.statusPostJobPosting.observe(this@PostJobPostingActivity) { status ->
            when (status) {
                is Resource.Loading -> {
                    progressCircular.show()
                }
                is Resource.Success -> {
                    progressCircular.hide()
                    finish()
                }
                is Resource.Error -> {
                    progressCircular.hide()
                    Toast.makeText(this@PostJobPostingActivity, "채용공고를 등록하는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getJobPostingItem(): JobPostingItem =
        JobPostingItem(
            title = binding.titleEdittext.text.toString(),
            techStack = binding.techDropdownTextview.text.toString(),
            incruitLink = binding.linkEdittext.text.toString(),
            career = binding.careerEdittext.text.toString(),
            startEndTime = binding.datePickerTextview.text.toString(),
            text = binding.contentEdittext.text.toString(),
            createdAt = getCurrentTime(),
            id = 0,
            viewCount = 0,
        )

    private fun getCurrentTime(): String =
        SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.getDefault()).format(System.currentTimeMillis())

}