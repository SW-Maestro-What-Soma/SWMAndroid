package com.example.swmandroid.ui.community.detail

import android.os.Bundle
import android.widget.Toast
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityDetailStudyBinding
import com.example.swmandroid.model.community.delete.DeleteItemInfo
import com.example.swmandroid.model.community.study.StudyItem
import com.example.swmandroid.ui.community.CommunityViewModel
import com.example.swmandroid.util.getEmailFromDataStore
import com.example.swmandroid.util.hideMyContentLayout
import com.example.swmandroid.util.showMyContentLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailStudyActivity : BaseActivity<ActivityDetailStudyBinding>({ ActivityDetailStudyBinding.inflate(it) }) {

    private val communityViewModel: CommunityViewModel by viewModel()

    private var studyItem : StudyItem? = null

    private var deleteDialog : MaterialAlertDialogBuilder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initMyContentLayout()
        buttonClick()
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

    private fun buttonClick() = with(binding) {
        studyDeleteButton.setOnClickListener {
           showDeleteDialog()
        }
    }

    private fun getDeleteItemInfo() : DeleteItemInfo =
        DeleteItemInfo(
            studyItem?.id ?: 0,
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
                communityViewModel.deleteStudyPost(getDeleteItemInfo())
                communityViewModel.statusDeleteStudyPost.observe(this@DetailStudyActivity){ deleteResponse ->
                    if(deleteResponse.code() == 200){
                        Toast.makeText(this@DetailStudyActivity, "삭제 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(this@DetailStudyActivity, "삭제를 실패하셨습니다.", Toast.LENGTH_SHORT).show()
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