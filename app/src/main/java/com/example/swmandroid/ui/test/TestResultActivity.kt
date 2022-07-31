package com.example.swmandroid.ui.test

import android.os.Bundle
import com.example.swmandroid.base.BaseActivity
import com.example.swmandroid.databinding.ActivityTestResultBinding

class TestResultActivity : BaseActivity<ActivityTestResultBinding>({ ActivityTestResultBinding.inflate(it) }) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}