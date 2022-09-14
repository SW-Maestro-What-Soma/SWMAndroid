package com.example.swmandroid.ui.community.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityDetailStudyBinding
import com.example.swmandroid.model.community.delete.DeleteItemInfo
import com.example.swmandroid.model.community.study.StudyItem
import com.example.swmandroid.ui.community.CommunityViewModel
import com.example.swmandroid.ui.community.post.PostStudyActivity
import com.example.swmandroid.util.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailStudyActivity : BaseActivity<ActivityDetailStudyBinding>({ ActivityDetailStudyBinding.inflate(it) }) {

    private val communityViewModel: CommunityViewModel by viewModel()

    private var deleteDialog: MaterialAlertDialogBuilder? = null

    private var studyItem: StudyItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initStudyItem()
        initView()
        initMyContentLayout()
        buttonClick()
    }

    override fun onResume() {
        super.onResume()

        communityViewModel.getStudy(studyItem?.id ?: -1)
    }

    private fun initStudyItem() {
        studyItem = intent.getParcelableExtra("studyItem")
    }

    private fun initView() = with(binding) {
        communityViewModel.study.observe(this@DetailStudyActivity) { studyResponse ->
            when (studyResponse) {
                is Resource.Loading -> {
                    showProgressCircular(progressCircular)
                }

                is Resource.Success -> {
                    hideProgressCircular(progressCircular)

                    studyTitle.text = studyResponse.data?.title
                    studyCategory.text = studyResponse.data?.techStack
                    studyPerweek.text = getString(R.string.per_week_text, studyResponse.data?.perWeek)
                    studyDayofweek.text = studyResponse.data?.dayOfTheWeek
                    studyOnoff.text = if (studyResponse.data?.onOffline == true) "온라인" else "오프라인"
                    studyMintier.text = studyResponse.data?.minGrade
                    studyMaxtier.text = studyResponse.data?.maxGrade
                    studyViewcount.text = studyResponse.data?.viewCount.toString()
                    studyCommentcount.text = studyResponse.data?.commentCount.toString()
                    studyCreatedat.text = studyResponse.data?.createdAt
                    studyLink.text = studyResponse.data?.meetingLink
                    studyContent.text = studyResponse.data?.text

                    //TODO 닉네임 연결해야함
                }

                is Resource.Error -> {
                    hideProgressCircular(progressCircular)
                }
            }
        }
    }

    private fun initMyContentLayout() = with(binding) {
        if (checkUserEmail()) {
            showMyContentLayout(myContentLayout)
        } else {
            hideMyContentLayout(myContentLayout)
        }
    }

    private fun checkUserEmail(): Boolean =
        studyItem?.userEmail == getEmailFromDataStore()

    private fun buttonClick() = with(binding) {
        studyDeleteButton.setOnClickListener {
            showDeleteDialog()
        }

        studyModifyButton.setOnClickListener {
            val intent = Intent(this@DetailStudyActivity, PostStudyActivity::class.java)
            intent.putExtra("modify", studyItem)
            startActivity(intent)
        }
    }

    private fun getDeleteItemInfo(): DeleteItemInfo =
        DeleteItemInfo(
            studyItem?.id ?: -1,
            getEmailFromDataStore(),
        )

    private fun showDeleteDialog() {
        deleteDialog = MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.delete))
            .setMessage(resources.getString(R.string.dialog_delete_title))
            .setNeutralButton(resources.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.ok)) { _, _ ->
                communityViewModel.deleteStudyPost(getDeleteItemInfo())
                communityViewModel.statusDeleteStudyPost.observe(this@DetailStudyActivity) { deleteResponse ->
                    if (deleteResponse.code() == 200) {
                        Toast.makeText(this@DetailStudyActivity, "삭제 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
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