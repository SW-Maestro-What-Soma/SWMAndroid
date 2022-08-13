package com.example.swmandroid.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentEmailLoginBinding
import com.example.swmandroid.util.*

class EmailLoginFragment : BaseFragment<FragmentEmailLoginBinding>() {

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentEmailLoginBinding {
        return FragmentEmailLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkEmailEditText(binding.emailEdittext)
        checkPasswordEditText(binding.passwordEdittext)
        buttonClick()
    }

    private fun buttonClick() = with(binding) {
        backButton.setOnClickListener { root.findNavController().popBackStack() }
        loginButton.setOnClickListener {
            if (checkEmail(emailEdittext) && checkPassword(passwordEdittext)) {
                Toast.makeText(context, "로그인 성공하였습니다.", Toast.LENGTH_SHORT).show()
                root.findNavController().navigate(R.id.action_emailLoginFragment_to_mainActivity)
            } else {
                Toast.makeText(context, "이메일과 비밀번호 형식을 확인하세요.", Toast.LENGTH_SHORT).show()
            }
        }
        signInTextview.setOnClickListener { root.findNavController().navigate(R.id.action_emailLoginFragment_to_signInFragment) }
        findPasswordTextview.setOnClickListener { root.findNavController().navigate(R.id.action_emailLoginFragment_to_findPasswordFragment) }
    }

}