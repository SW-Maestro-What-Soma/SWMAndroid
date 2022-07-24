package com.example.swmandroid.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.swmandroid.base.BaseFragment
import com.example.swmandroid.databinding.FragmentFindPasswordBinding
import com.example.swmandroid.util.checkEmail
import com.example.swmandroid.util.checkEmailEditText

class FindPasswordFragment : BaseFragment<FragmentFindPasswordBinding>() {

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFindPasswordBinding {
        return FragmentFindPasswordBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkEmailEditText(binding.findPasswordEdittext)
        buttonClick()
    }

    private fun buttonClick() = with(binding) {
        backButton.setOnClickListener { root.findNavController().popBackStack() }
        findPasswordButton.setOnClickListener {
            if (checkEmail(findPasswordEdittext)) {
                //TODO 임시 비밀번호 발급 로직 추가해야함
                Toast.makeText(context, "이메일을 확인해주세요.", Toast.LENGTH_SHORT).show()
                root.findNavController().popBackStack()
            } else {
                Toast.makeText(context, "이메일 형식을 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}