package com.example.chatapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.api.response.ApiResponse
import com.example.chatapp.databinding.FragmentProfileBinding
import com.example.chatapp.viewModels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: UserViewModel by viewModels()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_chatsFragment)
        }
        binding.btnEdit.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }

        viewModel.userResponse.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Failure -> Toast.makeText(
                    context,
                    it.errorMessage,
                    Toast.LENGTH_LONG
                ).show()

                ApiResponse.Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_LONG).show()
                is ApiResponse.Success -> {
                    binding.etTvNumber.text = it.data.phone
                    binding.profileCity.text = it.data.city
                    binding.profileBirthday.text = it.data.birthday
                    binding.tvName.text = it.data.name
                    binding.etTvUserName.text = it.data.userName

                    var date = it.data.birthday


                    val format = SimpleDateFormat()
                    format.applyPattern("d.MM")
                    val docDate: Date = format.parse(date.toString())

                    if(22.11 <= docDate.toString().toDouble() && docDate.toString().toDouble() < 21.12 ){
                        binding.profileZodiac.text = "Sagittarius"
                    }
                }

            }
        }
    }
}