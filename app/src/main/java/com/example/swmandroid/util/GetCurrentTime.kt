package com.example.swmandroid.util

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentTime(): String =
    SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(System.currentTimeMillis())