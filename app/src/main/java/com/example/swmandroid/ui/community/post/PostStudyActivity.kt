package com.example.swmandroid.ui.community.post

import android.os.Bundle
import android.widget.CompoundButton.OnCheckedChangeListener
import android.widget.Toast
import com.example.swmandroid.GlobalApplication
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityPostStudyBinding
import com.example.swmandroid.model.community.study.StudyItem
import com.example.swmandroid.ui.community.CommunityViewModel
import com.example.swmandroid.util.Resource
import com.example.swmandroid.util.getCurrentTime
import com.example.swmandroid.util.hideProgressCircular
import com.example.swmandroid.util.showProgressCircular
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostStudyActivity : BaseActivity<ActivityPostStudyBinding>({ ActivityPostStudyBinding.inflate(it) }) {

    private val communityViewModel: CommunityViewModel by viewModel()

    private var techStackDialog: MaterialAlertDialogBuilder? = null
    private var onOffDialog: MaterialAlertDialogBuilder? = null
    private var perWeekDialog: MaterialAlertDialogBuilder? = null
    private var minTierDialog: MaterialAlertDialogBuilder? = null
    private var maxTierDialog: MaterialAlertDialogBuilder? = null

    private val techStackItems = arrayOf("Backend", "Frontend", "Android", "IOS", "DataScience", "DataAnalysis")
    private val onOffItems = arrayOf("온라인", "오프라인")
    private val perWeekItems = arrayOf("주1회", "주2회", "주3회", "주4회", "주5회", "주6회", "주7회")
    private val tierItems = arrayOf(
        "브론즈Ⅰ", "브론즈Ⅱ", "브론즈Ⅲ", "브론즈Ⅳ", "브론즈Ⅴ",
        "실버Ⅰ", "실버Ⅱ", "실버Ⅲ", "실버Ⅳ", "실버Ⅴ",
        "골드Ⅰ", "골드Ⅱ", "골드Ⅲ", "골드Ⅳ", "골드Ⅴ",
        "플래티넘Ⅰ", "플래티넘Ⅱ", "플래티넘Ⅲ", "플래티넘Ⅳ", "플래티넘Ⅴ",
        "다이아몬드Ⅰ", "다이아몬드Ⅱ", "다이아몬드Ⅲ", "다이아몬드Ⅳ", "다이아몬드Ⅴ"
    )

    private var selectedTechStack = "Backend"
    private var selectedOnOff = "온라인"
    private var selectedPerWeek = "주1회"
    private var selectedMinTier = "브론즈Ⅰ"
    private var selectedMaxTier = "브론즈Ⅰ"

    private var checkedTechStackItemIdx = 0
    private var checkedOnOffItemIdx = 0
    private var checkedPerWeekItemIdx = 0
    private var checkedMinTierItemIdx = 0
    private var checkedMaxTierItemIdx = 0

    private var changeChecker: OnCheckedChangeListener? = null
    private val checkedDayList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initChangeChecker()
        hideProgressCircular(binding.progressCircular)
        buttonClick()
        dayButtonClick()
    }

    private fun initChangeChecker() {
        changeChecker = OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (buttonView == binding.mondayToggleButton) {
                    checkedDayList.add("월")
                }
                if (buttonView == binding.tuesdayToggleButton) {
                    checkedDayList.add("화")
                }
                if (buttonView == binding.wednesdayToggleButton) {
                    checkedDayList.add("수")
                }
                if (buttonView == binding.thursdayToggleButton) {
                    checkedDayList.add("목")
                }
                if (buttonView == binding.fridayToggleButton) {
                    checkedDayList.add("금")
                }
                if (buttonView == binding.saturdayToggleButton) {
                    checkedDayList.add("토")
                }
                if (buttonView == binding.sundayToggleButton) {
                    checkedDayList.add("일")
                }
            } else {
                if (buttonView == binding.mondayToggleButton) {
                    checkedDayList.remove("월")
                }
                if (buttonView == binding.tuesdayToggleButton) {
                    checkedDayList.remove("화")
                }
                if (buttonView == binding.wednesdayToggleButton) {
                    checkedDayList.remove("수")
                }
                if (buttonView == binding.thursdayToggleButton) {
                    checkedDayList.remove("목")
                }
                if (buttonView == binding.fridayToggleButton) {
                    checkedDayList.remove("금")
                }
                if (buttonView == binding.saturdayToggleButton) {
                    checkedDayList.remove("토")
                }
                if (buttonView == binding.sundayToggleButton) {
                    checkedDayList.remove("일")
                }
            }
        }
    }

    private fun buttonClick() = with(binding) {
        techAlertTextview.setOnClickListener {
            showTechStackDialog()
        }

        onOffAlertTextview.setOnClickListener {
            showOnOffDialog()
        }

        perWeekAlertTextview.setOnClickListener {
            showPerWeekDialog()
        }

        minTierAlertTextview.setOnClickListener {
            showMinTierDialog()
        }

        maxTierAlertTextview.setOnClickListener {
            showMaxTierDialog()
        }

        writeButton.setOnClickListener {
            if (checkAllContentInput()) {
                postStudy()
            } else {
                Toast.makeText(this@PostStudyActivity, "모든 내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
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

    private fun showOnOffDialog() {
        onOffDialog = MaterialAlertDialogBuilder(this)
            .setTitle(R.string.on_off_input_hint)
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                binding.onOffAlertTextview.text = selectedOnOff
            }
            .setSingleChoiceItems(onOffItems, checkedOnOffItemIdx) { _, which ->
                selectedOnOff = onOffItems[which]
                checkedOnOffItemIdx = which
            }
        onOffDialog?.show()
    }

    private fun showPerWeekDialog() {
        perWeekDialog = MaterialAlertDialogBuilder(this)
            .setTitle(R.string.per_week_input_hint)
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                binding.perWeekAlertTextview.text = selectedPerWeek
            }
            .setSingleChoiceItems(perWeekItems, checkedPerWeekItemIdx) { _, which ->
                selectedPerWeek = perWeekItems[which]
                checkedPerWeekItemIdx = which
            }
        perWeekDialog?.show()
    }

    private fun showMinTierDialog() {
        minTierDialog = MaterialAlertDialogBuilder(this)
            .setTitle(R.string.min_tier_input_text)
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                binding.minTierAlertTextview.text = selectedMinTier
            }
            .setSingleChoiceItems(tierItems, checkedMinTierItemIdx) { _, which ->
                selectedMinTier = tierItems[which]
                checkedMinTierItemIdx = which
            }
        minTierDialog?.show()
    }

    private fun showMaxTierDialog() {
        maxTierDialog = MaterialAlertDialogBuilder(this)
            .setTitle(R.string.max_tier_input_hint)
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                binding.maxTierAlertTextview.text = selectedMaxTier
            }
            .setSingleChoiceItems(tierItems, checkedMaxTierItemIdx) { _, which ->
                selectedMaxTier = tierItems[which]
                checkedMaxTierItemIdx = which
            }
        maxTierDialog?.show()
    }

    private fun dayButtonClick() = with(binding) {
        mondayToggleButton.setOnCheckedChangeListener(changeChecker)
        tuesdayToggleButton.setOnCheckedChangeListener(changeChecker)
        wednesdayToggleButton.setOnCheckedChangeListener(changeChecker)
        thursdayToggleButton.setOnCheckedChangeListener(changeChecker)
        fridayToggleButton.setOnCheckedChangeListener(changeChecker)
        saturdayToggleButton.setOnCheckedChangeListener(changeChecker)
        sundayToggleButton.setOnCheckedChangeListener(changeChecker)
    }

    private fun checkAllContentInput(): Boolean {
        with(binding) {
            return (titleEdittext.text.isNotBlank()
                    && techAlertTextview.text != resources.getString(R.string.tech_drop_text)
                    && openChatLinkEdittext.text.toString() != resources.getString(R.string.open_chat_text)
                    && onOffAlertTextview.text != resources.getString(R.string.on_off_input_hint)
                    && perWeekAlertTextview.text != resources.getString(R.string.per_week_input_hint)
                    && getDay().isNotBlank()
                    && minTierAlertTextview.text != resources.getString(R.string.min_tier_input_text)
                    && maxTierAlertTextview.text != resources.getString(R.string.max_tier_input_hint)
                    && contentEdittext.text.isNotBlank()
                    )
        }
    }

    private fun postStudy() = with(binding) {
        val addItem = getPostItem()

        communityViewModel.postStudy(addItem)
        communityViewModel.statusPostStudy.observe(this@PostStudyActivity) { status ->
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
                    Toast.makeText(this@PostStudyActivity, "스터디 게시글을 등록하는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getPostItem(): StudyItem =
        StudyItem(
            title = binding.titleEdittext.text.toString(),
            techStack = binding.techAlertTextview.text.toString(),
            meetingLink = binding.openChatLinkEdittext.text.toString(),
            onOffline = binding.onOffAlertTextview.text.toString() == "온라인",
            perWeek = Character.getNumericValue(binding.perWeekAlertTextview.text[1]),
            dayOfTheWeek = getDay(),
            minGrade = binding.minTierAlertTextview.text.toString(),
            maxGrade = binding.maxTierAlertTextview.text.toString(),
            text = binding.contentEdittext.text.toString(),
            createdAt = getCurrentTime(),
            commentCount = 0,
            id = 0,
            userId = 0,
            viewCount = 0,
            userEmail = getEmailFromDataStore()
        )

    private fun getDay(): String {
        val stringBuilder = StringBuilder()

        for (i in 0 until checkedDayList.size) {
            stringBuilder.append(checkedDayList[i])

            if (i != checkedDayList.size - 1) {
                stringBuilder.append(", ")
            }
        }

        return stringBuilder.toString()
    }

    private fun getEmailFromDataStore(): String =
        runBlocking { GlobalApplication.getInstance().getDataStore().email.first() }

    override fun onDestroy() {
        super.onDestroy()
        techStackDialog = null
        onOffDialog = null
        perWeekDialog = null
        minTierDialog = null
        maxTierDialog = null
    }
}