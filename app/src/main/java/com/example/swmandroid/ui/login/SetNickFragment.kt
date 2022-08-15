package com.example.swmandroid.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.swmandroid.R
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentSetNickBinding
import com.example.swmandroid.model.login.LoginInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.regex.Pattern

class SetNickFragment : BaseFragment<FragmentSetNickBinding>() {

    private val loginViewModel: LoginViewModel by sharedViewModel()

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSetNickBinding {
        return FragmentSetNickBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkNickEditText(binding.setNickEdittext)
        buttonClick()
    }

    private fun buttonClick() = with(binding) {
        backButton.setOnClickListener { root.findNavController().popBackStack() }
        setNickButton.setOnClickListener {
            if (checkNickname(setNickEdittext)) {
                apiPostSignUp()
            } else {
                Toast.makeText(context, "닉네임 형식을 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkNickEditText(nicknameEditText: EditText) {
        nicknameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkNickname(nicknameEditText)
            }
        })
    }

    @SuppressLint("ResourceAsColor")
    private fun checkNickname(nicknameEditText: EditText): Boolean {
        val nickValidation = "^[가-힣ㄱ-ㅎa-zA-Z0-9]{2,}\$"
        val nick = nicknameEditText.text.toString().trim()
        val pattern = Pattern.matches(nickValidation, nick)
        return if (pattern) {
            nicknameEditText.setTextColor(R.color.black)
            true
        } else {
            nicknameEditText.setTextColor(-65536)
            false
        }
    }

    private fun apiPostSignUp() = with(binding) {
        val nickName = setNickEdittext.text.toString().trim()
        val args: SetNickFragmentArgs by navArgs()
        val userEntity = args.userEntity
        userEntity.nick_name = nickName

        CoroutineScope(Dispatchers.Main).launch {
            if (loginViewModel.postSignUp(userEntity)) {
                if (loginViewModel.postLogin(LoginInfo(userEntity.email, userEntity.user_pw))) {
                    root.findNavController().navigate(R.id.action_setNickFragment_to_mainActivity)
                } else {
                    Toast.makeText(context, "회원가입에 실패하였습니다..", Toast.LENGTH_SHORT).show()
                    root.findNavController().navigate(R.id.action_setNickFragment_to_loginFragment)
                }
            } else {
                Toast.makeText(context, "회원가입에 실패하였습니다..", Toast.LENGTH_SHORT).show()
                root.findNavController().navigate(R.id.action_setNickFragment_to_loginFragment)
            }
        }
    }

}