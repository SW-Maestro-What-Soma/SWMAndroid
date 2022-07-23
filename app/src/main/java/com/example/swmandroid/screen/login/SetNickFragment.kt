package com.example.swmandroid.screen.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.swmandroid.R
import com.example.swmandroid.databinding.FragmentSetNickBinding
import java.util.regex.Pattern

class SetNickFragment : Fragment() {

    private var _binding: FragmentSetNickBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSetNickBinding.inflate(inflater, container, false)

        checkNickEditText(binding.setNickEdittext)
        buttonClick()

        return binding.root
    }

    private fun buttonClick() = with(binding) {
        backButton.setOnClickListener { root.findNavController().popBackStack() }
        setNickButton.setOnClickListener {
            //TODO 이메일 가져와서 이메일+스택+닉네임 등록 로직 추가해야함
            if(checkNickname(setNickEdittext)){
                val techStack = requireArguments().getString("tech_stack")
                val nickName = setNickEdittext.text.toString().trim()
                root.findNavController().navigate(R.id.action_setNickFragment_to_mainActivity)
            }else {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}