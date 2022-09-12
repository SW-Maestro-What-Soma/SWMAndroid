package com.example.swmandroid.util

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout

fun showMyContentLayout(myContentLayout: ConstraintLayout) {
    myContentLayout.visibility = View.VISIBLE
}

fun hideMyContentLayout(myContentLayout: ConstraintLayout) {
    myContentLayout.visibility = View.GONE
}