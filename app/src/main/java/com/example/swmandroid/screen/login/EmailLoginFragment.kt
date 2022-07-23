package com.example.swmandroid.screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.swmandroid.R
import com.example.swmandroid.databinding.FragmentEmailLoginBinding
import com.example.swmandroid.util.*

class EmailLoginFragment : Fragment() {

    private var _binding: FragmentEmailLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmailLoginBinding.inflate(inflater, container, false)

        checkEmailEditText(binding.emailEdittext)
        checkPasswordEditText(binding.passwordEdittext)
        buttonClick()

        return binding.root
    }

    private fun buttonClick() = with(binding) {
        //TODO 처음 로그인한 사람인지 아닌지 확인하는 로직 추가해야함
        val isLoginFirst = true

        backButton.setOnClickListener { root.findNavController().popBackStack() }
        loginButton.setOnClickListener {
            if (checkEmail(emailEdittext) && checkPassword(passwordEdittext)) {
                Toast.makeText(context, "로그인 성공하였습니다.", Toast.LENGTH_SHORT).show()
                if (isLoginFirst) {
                    root.findNavController().navigate(R.id.action_emailLoginFragment_to_setTechFragment)
                } else {
                    root.findNavController().navigate(R.id.action_emailLoginFragment_to_mainActivity)
                }
            } else {
                Toast.makeText(context, "이메일과 비밀번호 형식을 확인하세요.", Toast.LENGTH_SHORT).show()
            }
        }
        signInTextview.setOnClickListener { root.findNavController().navigate(R.id.action_emailLoginFragment_to_signInFragment) }
        findPasswordTextview.setOnClickListener { root.findNavController().navigate(R.id.action_emailLoginFragment_to_findPasswordFragment) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}