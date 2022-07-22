package com.example.swmandroid.screen.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.swmandroid.R
import com.example.swmandroid.databinding.FragmentSignUpBinding
import com.example.swmandroid.util.checkEmail
import com.example.swmandroid.util.checkEmailEditText
import com.example.swmandroid.util.checkPassword
import com.example.swmandroid.util.checkPasswordEditText

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        buttonClick()
        checkEmailEditText(binding.emailEdittext)
        checkPasswordEditText(binding.passwordEdittext)
        checkRePasswordEditText()

        return binding.root
    }

    private fun buttonClick() = with(binding) {
        backButton.setOnClickListener { root.findNavController().popBackStack() }
        signUpButton.setOnClickListener {
            if (checkEmail(emailEdittext) && checkPassword(passwordEdittext) && checkRePassword()) {
                Toast.makeText(context, "회원가입 성공하였습니다.", Toast.LENGTH_SHORT).show()
                root.findNavController().popBackStack()
            } else {
                Toast.makeText(context, "이메일과 비밀번호 형식을 확인하세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkRePasswordEditText() {
        binding.rePasswordEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkRePassword()
            }
        })
    }

    @SuppressLint("ResourceAsColor")
    private fun checkRePassword(): Boolean = with(binding) {
        val onePassword = passwordEdittext.text.toString().trim()
        val twoPassword = rePasswordEdittext.text.toString().trim()

        return if (onePassword == twoPassword) {
            rePasswordEdittext.setTextColor(R.color.black)
            true
        } else {
            rePasswordEdittext.setTextColor(-65536)
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}