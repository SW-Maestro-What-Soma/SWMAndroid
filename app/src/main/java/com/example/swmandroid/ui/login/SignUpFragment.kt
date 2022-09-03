package com.example.swmandroid.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentSignUpBinding
import com.example.swmandroid.model.login.UserEntity
import com.example.swmandroid.util.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    private val loginViewModel: LoginViewModel by sharedViewModel()

    private var certification = ""

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressCircular.hide()

        initCertificationView()
        buttonClick()
        checkEmailEditText(requireContext(), binding.emailEdittext)
        checkPasswordEditText(requireContext(), binding.passwordEdittext)
        checkRePasswordEditText()
        checkCertificationEditText()
    }

    private fun initCertificationView() {
        loginViewModel.certification.observe(viewLifecycleOwner) {
            if (it.data != null) {
                showCertification()
                setTransmitTextview()

                if (loginViewModel.isCert) {
                    setBlockEmailAndCertificationView()
                } else {
                    binding.certificationEdittext.setTextColor(-65536)
                }
            }
        }

    }

    private fun buttonClick() = with(binding) {
        backButton.setOnClickListener { root.findNavController().popBackStack() }

        transmitTextview.setOnClickListener {
            certificationEdittext.text = null
            postEmailConfirm()
        }

        checkCertificationTextview.setOnClickListener {
            if (checkCertification()) {
                loginViewModel.setCertificationStatus(true)
                setBlockEmailAndCertificationView()
                Toast.makeText(requireContext(), "인증 되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                loginViewModel.setCertificationStatus(false)
                certificationEdittext.setTextColor(-65536)
                Toast.makeText(requireContext(), "인증번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        signUpButton.setOnClickListener {
            if (loginViewModel.isCert && checkEmail(requireContext(), emailEdittext) && checkPassword(requireContext(), passwordEdittext) && checkRePassword()) {
                moveSetTechFragment()
            } else {
                Toast.makeText(requireContext(), "이메일과 비밀번호 형식을 확인하세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun postEmailConfirm() = with(binding) {
        if (checkEmail(requireContext(), emailEdittext)) {
            setTransmitTextview()

            loginViewModel.postEmailConfirm(emailEdittext.text.toString())
            loginViewModel.certification.observe(viewLifecycleOwner) { certResponse ->
                when (certResponse) {
                    is Resource.Loading -> {
                        progressCircular.show()
                    }
                    is Resource.Success -> {
                        progressCircular.hide()
                        showCertification()
                        certification = certResponse.data?.certification ?: ""
                        Toast.makeText(requireContext(), "인증 이메일이 발송되었습니다!!", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Error -> {
                        progressCircular.hide()
                        Toast.makeText(requireContext(), "인증번호를 보내는데 실패하였습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            Toast.makeText(requireContext(), "이메일 형식을 확인하세요.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setTransmitTextview() {
        binding.transmitTextview.text = resources.getText(R.string.retransmit_text)
    }

    private fun showCertification() = with(binding) {
        certificationEdittext.visibility = View.VISIBLE
        checkCertificationTextview.visibility = View.VISIBLE
    }

    private fun checkCertification(): Boolean =
        binding.certificationEdittext.text.toString() == certification

    private fun setBlockEmailAndCertificationView() = with(binding) {
        emailEdittext.apply {
            setBackgroundResource(R.drawable.black_gray_background)
            isEnabled = false
        }
        certificationEdittext.apply {
            setBackgroundResource(R.drawable.black_gray_background)
            isEnabled = false
        }
        transmitTextview.apply {
            setBackgroundResource(R.drawable.black_gray_background)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            isEnabled = false
        }
        checkCertificationTextview.apply {
            setBackgroundResource(R.drawable.black_gray_background)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            isEnabled = false
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

    private fun checkRePassword(): Boolean = with(binding) {
        val onePassword = passwordEdittext.text.toString().trim()
        val twoPassword = rePasswordEdittext.text.toString().trim()

        return if (onePassword == twoPassword) {
            rePasswordEdittext.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            true
        } else {
            rePasswordEdittext.setTextColor(-65536)
            false
        }
    }

    private fun checkCertificationEditText() {
        binding.certificationEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                binding.certificationEdittext.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            }

        })
    }

    private fun moveSetTechFragment() {
        val action = SignUpFragmentDirections.actionSignUpFragmentToSetTechFragment(getEdittextText())
        binding.root.findNavController().navigate(action)
    }

    private fun getEdittextText(): UserEntity = with(binding) {
        val email = emailEdittext.text.toString()
        val password = passwordEdittext.text.toString()

        return UserEntity(
            email = email,
            user_pw = password,
        )
    }

}