package com.example.chatapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.api.request.RegisterRequest
import com.example.chatapp.api.response.ApiResponse
import com.example.chatapp.databinding.FragmentRegistrationBinding
import com.example.chatapp.viewModels.CoroutinesErrorHandler
import com.example.chatapp.viewModels.RegisterViewModel
import com.example.chatapp.viewModels.TokenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModels()
    private val tokenViewModel: TokenViewModel by activityViewModels()



    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val phone = binding.tvPhone.text
        val name = binding.etName.text
        val userName = binding.etUsername.text

        binding.btnSignUp.setOnClickListener {

            viewModel.register(
                RegisterRequest(phone.toString(), userName.toString(), name.toString()),
                object : CoroutinesErrorHandler {
                    override fun onError(message: String) {
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    }
                })
            tokenViewModel.token.observe(viewLifecycleOwner) { token ->
                if (token != null)
                    findNavController().navigate(R.id.action_registrationFragment_to_chatsFragment)
            }

            viewModel.registerResponse.observe(viewLifecycleOwner) {
                when (it) {
                    is ApiResponse.Failure -> Toast.makeText(
                        context,
                        it.errorMessage,
                        Toast.LENGTH_LONG
                    ).show()

                    ApiResponse.Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_LONG)
                        .show()

                    is ApiResponse.Success -> {
                        tokenViewModel.saveToken(it.data.accessToken)
                    }
                }
            }
        }
    }
    }
