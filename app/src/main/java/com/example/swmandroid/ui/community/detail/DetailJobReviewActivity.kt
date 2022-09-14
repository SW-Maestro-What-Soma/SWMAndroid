package com.example.swmandroid.ui.community.detail

import android.os.Bundle
import android.widget.Toast
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityDetailJobreviewBinding
import com.example.swmandroid.model.community.delete.DeleteItemInfo
import com.example.swmandroid.model.community.jobreview.JobReviewItem
import com.example.swmandroid.ui.community.CommunityViewModel
import com.example.swmandroid.util.getEmailFromDataStore
import com.example.swmandroid.util.hideMyContentLayout
import com.example.swmandroid.util.showMyContentLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailJobReviewActivity : BaseActivity<ActivityDetailJobreviewBinding>({ ActivityDetailJobreviewBinding.inflate(it) }) {

    private val communityViewModel: CommunityViewModel by viewModel()

    private var jobReviewItem: JobReviewItem? = null

    private var deleteDialog : MaterialAlertDialogBuilder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initMyContentLayout()
        buttonClick()
    }

    private fun initView() = with(binding) {
        jobReviewItem = intent.getParcelableExtra("JobReview")

        jobreviewTitle.text = jobReviewItem?.title
        jobreviewCategory.text = jobReviewItem?.techStack
        jobreviewProgress.text = jobReviewItem?.processCategory
        jobreviewViewcount.text = jobReviewItem?.viewCount.toString()
        jobreviewCommentcount.text = jobReviewItem?.commentCount.toString()
        jobreviewCreatedat.text = jobReviewItem?.createdAt
        jobreviewContent.text = jobReviewItem?.text

        //TODO 닉네임 연결해야함
        //TODO 댓글 연결해야함
    }

    private fun initMyContentLayout() = with(binding) {
        if(checkUserEmail()){
            showMyContentLayout(myContentLayout)
        }else{
            hideMyContentLayout(myContentLayout)
        }
    }

    private fun checkUserEmail(): Boolean =
        jobReviewItem?.userEmail == getEmailFromDataStore()

    private fun buttonClick() = with(binding) {
        jobreviewDeleteButton.setOnClickListener {
            showDeleteDialog()
        }
    }

    private fun getDeleteItemInfo(): DeleteItemInfo =
        DeleteItemInfo(
            jobReviewItem?.id ?: 0,
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