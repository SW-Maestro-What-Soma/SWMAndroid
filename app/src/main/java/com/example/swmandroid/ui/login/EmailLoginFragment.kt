package com.example.swmandroid.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.swmandroid.GlobalApplication
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentEmailLoginBinding
import com.example.swmandroid.model.login.LoginInfo
import com.example.swmandroid.model.login.UserProfile
import com.example.swmandroid.util.Resource
import com.example.swmandroid.util.checkEmail
import com.example.swmandroid.util.checkEmailEditText
import com.example.swmandroid.util.saveUserProfileAtDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EmailLoginFragment : BaseFragment<FragmentEmailLoginBinding>() {

    private val loginViewModel: LoginViewModel by sharedViewModel()

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentEmailLoginBinding {
        return FragmentEmailLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressCircular.hide()
        checkEmailEditText(requireContext(), binding.emailEdittext)

        buttonClick()
    }

    private fun buttonClick() = with(binding) {
        backButton.setOnClickListener { root.findNavController().popBackStack() }
        loginButton.setOnClickListener {
            if (checkEmail(requireContext(), emailEdittext)) {
                apiPostLogin()
            } else {
                Toast.makeText(requireContext(), "이메일과 비밀번호 형식을 확인하세요.", Toast.LENGTH_SHORT).show()
            }
        }
        signInTextview.setOnClickListener { root.findNavController().navigate(R.id.action_emailLoginFragment_to_signUpFragment) }
        findPasswordTextview.setOnClickListener { root.findNavController().navigate(R.id.action_emailLoginFragment_to_findPasswordFragment) }
    }

    private fun apiPostLogin() = with(binding) {
        val email = emailEdittext.text.toString()
        val password = passwordEdittext.text.toString()

        loginViewModel.postLogin(LoginInfo(email, password))
        loginViewModel.userProfile.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    progressCircular.show()
                }
                is Resource.Success -> {
                    progressCircular.hide()
                    saveUserProfileAtDataStore(it.data)
                    root.findNavController().navigate(R.id.action_emailLoginFragment_to_mainActivity)
                }
                is Resource.Error -> {
                    progressCircular.hide()
                    Toast.makeText(requireContext(), "로그인 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}