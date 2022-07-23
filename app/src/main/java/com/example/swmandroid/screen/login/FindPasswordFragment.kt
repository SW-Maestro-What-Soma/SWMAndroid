package com.example.swmandroid.screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.swmandroid.databinding.FragmentFindPasswordBinding
import com.example.swmandroid.util.checkEmail
import com.example.swmandroid.util.checkEmailEditText

class FindPasswordFragment : Fragment() {

    private var _binding: FragmentFindPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFindPasswordBinding.inflate(inflater, container, false)

        checkEmailEditText(binding.findPasswordEdittext)
        buttonClick()

        return binding.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}