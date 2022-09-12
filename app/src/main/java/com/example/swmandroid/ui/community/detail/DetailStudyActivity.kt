package com.example.swmandroid.ui.community.detail

import android.os.Bundle
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityDetailStudyBinding
import com.example.swmandroid.model.community.study.StudyItem
import com.example.swmandroid.util.getEmailFromDataStore
import com.example.swmandroid.util.hideMyContentLayout
import com.example.swmandroid.util.showMyContentLayout

class DetailStudyActivity : BaseActivity<ActivityDetailStudyBinding>({ ActivityDetailStudyBinding.inflate(it) }) {

    private var studyItem : StudyItem? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initMyContentLayout()
    }

    private fun initView() = with(binding) {
        studyItem = intent.getParcelableExtra<StudyItem>("Study")

        studyTitle.text = studyItem?.title
        studyCategory.text = studyItem?.techStack
        studyPerweek.text = getString(R.string.per_week_text, studyItem?.perWeek)
        studyDayofweek.text = studyItem?.dayOfTheWeek
        studyOnoff.text = if (studyItem?.onOffline == true) "온라인" else "오프라인"
        studyMintier.text = studyItem?.minGrade
        studyMaxtier.text = studyItem?.maxGrade
        studyViewcount.text = studyItem?.viewCount.toString()
        studyCommentcount.text = studyItem?.commentCount.toString()
        studyCreatedat.text = studyItem?.createdAt
        studyLink.text = studyItem?.meetingLink
        studyContent.text = studyItem?.text

        //TODO 닉네임 변경
    }

    private fun initMyContentLayout() = with(binding) {
        if(checkUserEmail()){
            showMyContentLayout(myContentLayout)
        }else{
            hideMyContentLayout(myContentLayout)
        }
    }

    private fun checkUserEmail(): Boolean =
        studyItem?.userEmail == getEmailFromDataStore()

}