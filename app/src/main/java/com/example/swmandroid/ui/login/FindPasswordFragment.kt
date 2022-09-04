package com.example.swmandroid.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentFindPasswordBinding
import com.example.swmandroid.util.Resource
import com.example.swmandroid.util.checkEmail
import com.example.swmandroid.util.checkEmailEditText
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FindPasswordFragment : BaseFragment<FragmentFindPasswordBinding>() {

    private val loginViewModel: LoginViewModel by sharedViewModel()

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFindPasswordBinding {
        return FragmentFindPasswordBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressCircular.hide()

        checkEmailEditText(requireContext(), binding.findPasswordEdittext)
        buttonClick()
    }

    private fun buttonClick() = with(binding) {
        backButton.setOnClickListener { root.findNavController().popBackStack() }
        findPasswordButton.setOnClickListener {
            if (checkEmail(requireContext(), findPasswordEdittext)) {
                postSearchPassword()
            } else {
                Toast.makeText(requireContext(), "이메일 형식을 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun postSearchPassword() = with(binding) {
        loginViewModel.postSearchPassword(findPasswordEdittext.text.toString())
        loginViewModel.passwordToken.observe(viewLifecycleOwner) { certResponse ->
            when (certResponse) {
                is Resource.Loading -> {
                    progressCircular.show()
                }
                is Resource.Success -> {
                    progressCircular.hide()
                    Toast.makeText(requireContext(), "가입하신 이메일로 임시 비밀번호를 발급드렸습니다.", Toast.LENGTH_SHORT).show()
                    root.findNavController().popBackStack()
                }
                is Resource.Error -> {
                    progressCircular.hide()
                    Toast.makeText(requireContext(), "임시 비밀번호를 발급하는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

}