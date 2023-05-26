package com.example.chatapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.api.request.AuthRequest
import com.example.chatapp.api.request.CodeRequest
import com.example.chatapp.api.response.ApiResponse
import com.example.chatapp.databinding.FragmentAuthBinding
import com.example.chatapp.viewModels.AuthViewModel
import com.example.chatapp.viewModels.CodeViewModel
import com.example.chatapp.viewModels.CoroutinesErrorHandler
import com.example.chatapp.viewModels.TokenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModels()
    private val codeViewModel: CodeViewModel by viewModels()
    private val tokenViewModel: TokenViewModel by activityViewModels()

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fullNumber = binding.cpp.registerCarrierNumberEditText(binding.editTextPhone)
        val code = binding.enterCode.text

        binding.enterPhone.setOnClickListener {

            viewModel.auth(
                AuthRequest(fullNumber.toString()),
                object : CoroutinesErrorHandler {
                    override fun onError(message: String) {
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    }
                })
        }
        binding.sendCode.setOnClickListener {


            codeViewModel.code(
                CodeRequest(
                    fullNumber.toString(), code.toString()
                ), object : CoroutinesErrorHandler {
                    override fun onError(message: String) {
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    }
                })

            codeViewModel.codeResponse.observe(viewLifecycleOwner) {
                when (it) {
                    is ApiResponse.Failure -> Toast.makeText(context, it.errorMessage, Toast.LENGTH_LONG).show()
                    ApiResponse.Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
                    is ApiResponse.Success -> {
                        tokenViewModel.saveToken(it.data.accessToken)
                        if(it.data.isUserExists){
                            findNavController().navigate(R.id.action_authFragment_to_chatsFragment)
                        } else if(!it.data.isUserExists){
                            findNavController().navigate(R.id.action_authFragment_to_registrationFragment)
                        }
                    }
                }
            }
        }
    }
}