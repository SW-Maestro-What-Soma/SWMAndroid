package com.example.swmandroid.ui.community.detail

import android.os.Bundle
import android.widget.Toast
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityDetailJobpostingBinding
import com.example.swmandroid.model.community.delete.DeleteItemInfo
import com.example.swmandroid.ui.community.CommunityViewModel
import com.example.swmandroid.util.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailJobPostingActivity : BaseActivity<ActivityDetailJobpostingBinding>({ ActivityDetailJobpostingBinding.inflate(it) }) {

    private val communityViewModel: CommunityViewModel by viewModel()

    private var deleteDialog: MaterialAlertDialogBuilder? = null

    private var postId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPostId()
        initView()
        initMyContentLayout()
        buttonClick()
    }

    private fun initPostId() {
        postId = intent.getIntExtra("postId", -1)
    }

    private fun initView() = with(binding) {
        communityViewModel.getJobPosting(postId)
        communityViewModel.jobPosting.observe(this@DetailJobPostingActivity) { jobPostingResponse ->
            when (jobPostingResponse) {
                is Resource.Loading -> {
                    showProgressCircular(progressCircular)
                }

                is Resource.Success -> {
                    hideProgressCircular(progressCircular)

                    jobpostingTitle.text = jobPostingResponse.data?.title
                    jobpostingCategory.text = jobPostingResponse.data?.techStack
                    jobpostingCareer.text = jobPostingResponse.data?.career
                    jobpostingViewcount.text = jobPostingResponse.data?.viewCount.toString()
                    jobpostingCreatedat.text = jobPostingResponse.data?.createdAt
                    jobpostingLink.text = jobPostingResponse.data?.incruitLink
                    jobpostingStartEndTime.text = jobPostingResponse.data?.startEndTime
                    jobpostingContent.text = jobPostingResponse.data?.text
                    //TODO 닉네임 연결해야함
                }

                is Resource.Error -> {
                    hideProgressCircular(progressCircular)
                }
            }
        }
    }

    private fun initMyContentLayout() = with(binding) {
        if (checkUserRole()) {
            showMyContentLayout(myContentLayout)
        } else {
            hideMyContentLayout(myContentLayout)
        }
    }

    private fun checkUserRole(): Boolean =
        getUserRoleFromDataStore() == "MANAGER"

    private fun buttonClick() = with(binding) {
        jobpostingDeleteButton.setOnClickListener {
            showDeleteDialog()
        }
    }

    private fun getDeleteItemInfo(): DeleteItemInfo =
        DeleteItemInfo(
            postId,
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
                communityViewModel.deleteJobPostingPost(getDeleteItemInfo())
                communityViewModel.statusDeleteJobPostingPost.observe(this@DetailJobPostingActivity) { deleteResponse ->
                    if (deleteResponse.code() == 200) {
                        Toast.makeText(this@DetailJobPostingActivity, "삭제 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@DetailJobPostingActivity, "삭제를 실패하셨습니다.", Toast.LENGTH_SHORT).show()
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