package com.example.swmandroid.ui.community

import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityCommunityBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommunityActivity : BaseActivity<ActivityCommunityBinding>({ ActivityCommunityBinding.inflate(it) }) {

    private val communityViewModel: CommunityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        buttonClick()
    }

    private fun buttonClick() = with(binding) {
        backButton.setOnClickListener {
            finish()
        }

        fullCommunityButton.setOnClickListener {
            selectedEvent(fullCommunityButton)

            val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            val nowFragment = hostFragment?.childFragmentManager?.fragments?.get(0)

            if (nowFragment is SubCommunityFragment) {
                nowFragment.moveFullCommunityFragment()
            }
        }

        jobpostingCommunityButton.setOnClickListener {
            selectedEvent(jobpostingCommunityButton)

            communityViewModel.setCategory("채용공고")

            val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            val nowFragment = hostFragment?.childFragmentManager?.fragments?.get(0)

            if (nowFragment is SubCommunityFragment) {
                nowFragment.moveSubCommunityFragment()
            }
            if (nowFragment is FullCommunityFragment) {
                nowFragment.moveSubCommunityFragment()
            }
        }

        jobreviewCommunityButton.setOnClickListener {
            selectedEvent(jobreviewCommunityButton)

            communityViewModel.setCategory("채용후기")

            val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            val nowFragment = hostFragment?.childFragmentManager?.fragments?.get(0)

            if (nowFragment is SubCommunityFragment) {
                nowFragment.moveSubCommunityFragment()
            }
            if (nowFragment is FullCommunityFragment) {
                nowFragment.moveSubCommunityFragment()
            }
        }

        studyCommunityButton.setOnClickListener {
            selectedEvent(studyCommunityButton)

            communityViewModel.setCategory("스터디")

            val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            val nowFragment = hostFragment?.childFragmentManager?.fragments?.get(0)

            if (nowFragment is SubCommunityFragment) {
                nowFragment.moveSubCommunityFragment()
            }
            if (nowFragment is FullCommunityFragment) {
                nowFragment.moveSubCommunityFragment()
            }
        }

        questionCommunityButton.setOnClickListener {
            selectedEvent(questionCommunityButton)

            communityViewModel.setCategory("질문")

            val hostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
            val nowFragment = hostFragment?.childFragmentManager?.fragments?.get(0)

            if (nowFragment is SubCommunityFragment) {
                nowFragment.moveSubCommunityFragment()
            }
            if (nowFragment is FullCommunityFragment) {
                nowFragment.moveSubCommunityFragment()
            }
        }
    }

    private fun selectedEvent(button: TextView) = with(binding) {
        when (button) {
            fullCommunityButton -> selectedFull()
            jobpostingCommunityButton -> selectedJobPosting()
            jobreviewCommunityButton -> selectedJobReview()
            studyCommunityButton -> selectedStudy()
            questionCommunityButton -> selectedQuestion()
        }
    }

    private fun selectedFull() = with(binding) {
        fullCommunityButton.setBackgroundResource(R.drawable.selected_tab)
        jobpostingCommunityButton.setBackgroundResource(R.drawable.unselected_tab)
        jobreviewCommunityButton.setBackgroundResource(R.drawable.unselected_tab)
        studyCommunityButton.setBackgroundResource(R.drawable.unselected_tab)
        questionCommunityButton.setBackgroundResource(R.drawable.unselected_tab)

        fullCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.white))
        jobpostingCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
        jobreviewCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
        studyCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
        questionCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
    }

    private fun selectedJobPosting() = with(binding) {
        fullCommunityButton.setBackgroundResource(R.drawable.unselected_tab)
        jobpostingCommunityButton.setBackgroundResource(R.drawable.selected_tab)
        jobreviewCommunityButton.setBackgroundResource(R.drawable.unselected_tab)
        studyCommunityButton.setBackgroundResource(R.drawable.unselected_tab)
        questionCommunityButton.setBackgroundResource(R.drawable.unselected_tab)

        fullCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
        jobpostingCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.white))
        jobreviewCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
        studyCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
        questionCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
    }

    private fun selectedJobReview() = with(binding) {
        fullCommunityButton.setBackgroundResource(R.drawable.unselected_tab)
        jobpostingCommunityButton.setBackgroundResource(R.drawable.unselected_tab)
        jobreviewCommunityButton.setBackgroundResource(R.drawable.selected_tab)
        studyCommunityButton.setBackgroundResource(R.drawable.unselected_tab)
        questionCommunityButton.setBackgroundResource(R.drawable.unselected_tab)

        fullCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
        jobpostingCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
        jobreviewCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.white))
        studyCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
        questionCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
    }

    private fun selectedStudy() = with(binding) {
        fullCommunityButton.setBackgroundResource(R.drawable.unselected_tab)
        jobpostingCommunityButton.setBackgroundResource(R.drawable.unselected_tab)
        jobreviewCommunityButton.setBackgroundResource(R.drawable.unselected_tab)
        studyCommunityButton.setBackgroundResource(R.drawable.selected_tab)
        questionCommunityButton.setBackgroundResource(R.drawable.unselected_tab)

        fullCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
        jobpostingCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
        jobreviewCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
        studyCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.white))
        questionCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
    }

    private fun selectedQuestion() = with(binding) {
        fullCommunityButton.setBackgroundResource(R.drawable.unselected_tab)
        jobpostingCommunityButton.setBackgroundResource(R.drawable.unselected_tab)
        jobreviewCommunityButton.setBackgroundResource(R.drawable.unselected_tab)
        studyCommunityButton.setBackgroundResource(R.drawable.unselected_tab)
        questionCommunityButton.setBackgroundResource(R.drawable.selected_tab)

        fullCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
        jobpostingCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
        jobreviewCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
        studyCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.gray))
        questionCommunityButton.setTextColor(ContextCompat.getColor(this@CommunityActivity, R.color.white))
    }

    fun setTopCategoryPosition(position: Int) {
        when (position) {
            0 -> selectedJobPosting()
            1 -> selectedJobReview()
            2 -> selectedStudy()
            3 -> selectedQuestion()
        }
    }
}