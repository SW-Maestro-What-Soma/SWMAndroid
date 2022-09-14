package com.example.swmandroid.ui.community.detail

import android.os.Bundle
import android.widget.Toast
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityDetailJobpostingBinding
import com.example.swmandroid.model.community.delete.DeleteItemInfo
import com.example.swmandroid.model.community.jobposting.JobPostingItem
import com.example.swmandroid.ui.community.CommunityViewModel
import com.example.swmandroid.util.getEmailFromDataStore
import com.example.swmandroid.util.getUserRoleFromDataStore
import com.example.swmandroid.util.hideMyContentLayout
import com.example.swmandroid.util.showMyContentLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailJobPostingActivity : BaseActivity<ActivityDetailJobpostingBinding>({ ActivityDetailJobpostingBinding.inflate(it) }) {

    private val communityViewModel: CommunityViewModel by viewModel()

    private var jobPostingItem: JobPostingItem? = null

    private var deleteDialog : MaterialAlertDialogBuilder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initMyContentLayout()
        buttonClick()
    }

    private fun initView() = with(binding) {
        jobPostingItem = intent.getParcelableExtra<JobPostingItem>("JobPosting")

        jobpostingTitle.text = jobPostingItem?.title
        jobpostingCategory.text = jobPostingItem?.techStack
        jobpostingCareer.text = jobPostingItem?.career
        jobpostingViewcount.text = jobPostingItem?.viewCount.toString()
        jobpostingCreatedat.text = jobPostingItem?.createdAt
        jobpostingLink.text = jobPostingItem?.incruitLink
        jobpostingStartEndTime.text = jobPostingItem?.startEndTime
        jobpostingContent.text = jobPostingItem?.text
        //TODO 닉네임 연결해야함
    }

    private fun initMyContentLayout() = with(binding){
        if(checkUserRole()){
            showMyContentLayout(myContentLayout)
        }else{
            hideMyContentLayout(myContentLayout)
        }
    }

    private fun checkUserRole() : Boolean =
        getUserRoleFromDataStore() == "MANAGER"

    private fun buttonClick() = with(binding) {
        jobpostingDeleteButton.setOnClickListener {
            showDeleteDialog()
        }
    }

    private fun getDeleteItemInfo(): DeleteItemInfo =
        DeleteItemInfo(
            jobPostingItem?.id ?: 0,
            getEmailFromDataStore(),
        )

    private fun showDeleteDialog(){
        deleteDialog = MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.delete))
            .setMessage(resources.getString(R.string.dialog_delete_title))
            .setNeutralButton(resources.getString(R.string.cancel)){ dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.ok)){ _, _ ->
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