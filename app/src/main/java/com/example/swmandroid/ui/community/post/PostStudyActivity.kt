package com.example.swmandroid.ui.community.post

import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityPostStudyBinding
import com.example.swmandroid.model.community.study.StudyItem
import com.example.swmandroid.model.community.update.UpdateStudyItem
import com.example.swmandroid.ui.community.CommunityViewModel
import com.example.swmandroid.util.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
    private val dayItems = arrayOf("월", "화", "수", "목", "금", "토", "일")

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

    private val toggleButtonList = mutableListOf<CompoundButton>()

    private var studyItem: StudyItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getIntentData()
        initWriteButton()
        initToggleButtonList()
        hideProgressCircular(binding.progressCircular)
        buttonClick()
    }

    private fun getIntentData() {
        studyItem = intent.getParcelableExtra("modify")
    }

    private fun initWriteButton() = with(binding) {
        if (studyItem == null) {
            writeButton.text = resources.getString(R.string.write)
        } else {
            writeButton.text = resources.getString(R.string.modify_button_text)
            initView()
        }
    }

    private fun initToggleButtonList() = with(binding) {
        toggleButtonList.add(mondayToggleButton)
        toggleButtonList.add(tuesdayToggleButton)
        toggleButtonList.add(wednesdayToggleButton)
        toggleButtonList.add(thursdayToggleButton)
        toggleButtonList.add(fridayToggleButton)
        toggleButtonList.add(saturdayToggleButton)
        toggleButtonList.add(sundayToggleButton)
    }

    private fun initView() = with(binding) {
        titleEdittext.setText(studyItem?.title)
        techAlertTextview.text = studyItem?.techStack
        openChatLinkEdittext.setText(studyItem?.meetingLink)
        onOffAlertTextview.text = if (studyItem?.onOffline == true) "온라인" else "오프라인"
        perWeekAlertTextview.text = getString(R.string.per_week_text, studyItem?.perWeek)
        minTierAlertTextview.text = studyItem?.minGrade
        maxTierAlertTextview.text = studyItem?.maxGrade
        contentEdittext.setText(studyItem?.text)
        initToggleButton()
    }

    private fun initToggleButton() = with(binding) {
        val splitDay = studyItem?.dayOfTheWeek?.split(", ")

        splitDay?.forEach {
            when (it) {
                "월" -> mondayToggleButton.isChecked = true
                "화" -> tuesdayToggleButton.isChecked = true
                "수" -> wednesdayToggleButton.isChecked = true
                "목" -> thursdayToggleButton.isChecked = true
                "금" -> fridayToggleButton.isChecked = true
                "토" -> saturdayToggleButton.isChecked = true
                "일" -> sundayToggleButton.isChecked = true
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
                if (studyItem == null) {
                    postStudy()
                } else {
                    postUpdateStudy()
                }
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

    private fun postUpdateStudy() {
        val addItem = getUpdateStudyItem()

        communityViewModel.updateStudy(addItem)
        communityViewModel.updateStudyPost.observe(this@PostStudyActivity) { updateResponse ->
            if (updateResponse.code() == 200) {
                Toast.makeText(this@PostStudyActivity, "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this@PostStudyActivity, "수정이 실패하였습니다", Toast.LENGTH_SHORT).show()
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

        var commaIdx = 0
        toggleButtonList.forEachIndexed { index, compoundButton ->
            if(compoundButton.isChecked){
                if(commaIdx != 0){
                    stringBuilder.append(", ")
                }
                stringBuilder.append(dayItems[index])

                commaIdx++
            }
        }

        return stringBuilder.toString()
    }

    private fun getUpdateStudyItem(): UpdateStudyItem =
        UpdateStudyItem(
            dayOfTheWeek = getDay(),
            id = studyItem?.id ?: -1,
            maxGrade = binding.maxTierAlertTextview.text.toString(),
            meetingLink = binding.openChatLinkEdittext.text.toString(),
            minGrade = binding.minTierAlertTextview.text.toString(),
            onOffLine = binding.onOffAlertTextview.text == "온라인",
            perWeek = Character.getNumericValue(binding.perWeekAlertTextview.text[1]),
            techStack = binding.techAlertTextview.text.toString(),
            text = binding.contentEdittext.text.toString(),
            title = binding.titleEdittext.text.toString(),
            userEmail = getEmailFromDataStore(),
        )

    override fun onDestroy() {
        super.onDestroy()
        techStackDialog = null
        onOffDialog = null
        perWeekDialog = null
        minTierDialog = null
        maxTierDialog = null
    }
}