package com.example.swmandroid.util

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.example.swmandroid.R
import java.util.regex.Pattern

fun checkEmailEditText(context: Context, emailEdittext: EditText) {
    emailEdittext.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            checkEmail(context, emailEdittext)
        }
    })
}

fun checkPasswordEditText(context: Context, passwordEdittext: EditText) {
    passwordEdittext.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            checkPassword(context, passwordEdittext)
        }
    })
}

fun checkEmail(context: Context, emailEdittext: EditText): Boolean {
    val email = emailEdittext.text.toString().trim()
    val pattern = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    return if (pattern) {
        emailEdittext.setTextColor(ContextCompat.getColor(context, R.color.black))
        true
    } else {
        emailEdittext.setTextColor(-65536)
        false
    }
}

fun checkPassword(context: Context, passwordEdittext: EditText): Boolean {
    val passwordValidation = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$"
    val password = passwordEdittext.text.toString().trim()
    val pattern = Pattern.matches(passwordValidation, password)
    return if (pattern) {
        passwordEdittext.setTextColor(ContextCompat.getColor(context, R.color.black))
        true
    } else {
        passwordEdittext.setTextColor(-65536)
        false
    }
}