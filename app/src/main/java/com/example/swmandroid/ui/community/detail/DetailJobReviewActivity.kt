package com.example.swmandroid.ui.community.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityDetailJobreviewBinding
import com.example.swmandroid.model.community.delete.DeleteItemInfo
import com.example.swmandroid.model.community.jobreview.JobReviewItem
import com.example.swmandroid.ui.community.CommunityViewModel
import com.example.swmandroid.ui.community.post.PostJobReviewActivity
import com.example.swmandroid.util.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailJobReviewActivity : BaseActivity<ActivityDetailJobreviewBinding>({ ActivityDetailJobreviewBinding.inflate(it) }) {

    private val communityViewModel: CommunityViewModel by viewModel()

    private var deleteDialog: MaterialAlertDialogBuilder? = null

    private var jobReviewItem: JobReviewItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initJobReviewItem()
        initView()
        initMyContentLayout()
        buttonClick()
    }

    override fun onResume() {
        super.onResume()

        communityViewModel.getJobReview(jobReviewItem?.id ?: -1)
    }

    private fun initJobReviewItem() {
        jobReviewItem = intent.getParcelableExtra("jobReviewItem")
    }

    private fun initView() = with(binding) {
        communityViewModel.jobReview.observe(this@DetailJobReviewActivity) { jobReviewResponse ->
            when (jobReviewResponse) {
                is Resource.Loading -> {
                    showProgressCircular(progressCircular)
                }

                is Resource.Success -> {
                    hideProgressCircular(progressCircular)

                    jobreviewTitle.text = jobReviewResponse.data?.title
                    jobreviewCategory.text = jobReviewResponse.data?.techStack
                    jobreviewProgress.text = jobReviewResponse.data?.processCategory
                    jobreviewViewcount.text = jobReviewResponse.data?.viewCount.toString()
                    jobreviewCommentcount.text = jobReviewResponse.data?.commentCount.toString()
                    jobreviewCreatedat.text = jobReviewResponse.data?.createdAt

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
        jobReviewItem?.userEmail == getEmailFromDataStore()

    private fun buttonClick() = with(binding) {
        jobreviewDeleteButton.setOnClickListener {
            showDeleteDialog()
        }

        jobreviewModifyButton.setOnClickListener {
            val intent = Intent(this@DetailJobReviewActivity, PostJobReviewActivity::class.java)
            intent.putExtra("modify", jobReviewItem)
            startActivity(intent)
        }
    }

    private fun getDeleteItemInfo(): DeleteItemInfo =
        DeleteItemInfo(
            jobReviewItem?.id ?: -1,
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
                communityViewModel.deleteJobReviewPost(getDeleteItemInfo())
                communityViewModel.statusDeleteJobReviewPost.observe(this@DetailJobReviewActivity) { deleteResponse ->
                    if (deleteResponse.code() == 200) {
                        Toast.makeText(this@DetailJobReviewActivity, "삭제 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@DetailJobReviewActivity, "삭제를 실패하셨습니다.", Toast.LENGTH_SHORT).show()
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