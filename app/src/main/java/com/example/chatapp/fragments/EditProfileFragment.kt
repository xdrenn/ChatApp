package com.example.chatapp.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.api.request.UserRequest
import com.example.chatapp.databinding.FragmentEditProfileBinding
import com.example.chatapp.viewModels.CoroutinesErrorHandler
import com.example.chatapp.viewModels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    private val viewModel: UserViewModel by viewModels()

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedImage: AppCompatImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = binding.etProfileName.text
        val userName = binding.etTvUserName.text
        val birthday = binding.etBirthDate.text
        val city = binding.etCity.text
        val phone = binding.etTvNumber.text
        selectedImage = binding.profileImage


        binding.profileImage.setOnClickListener {
            val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            changeImage.launch(pickImg)
        }

        binding.btnCheck.setOnClickListener {
            findNavController().navigate(R.id.action_editProfileFragment_to_profileFragment)
            viewModel.user(
                UserRequest(
                    name.toString(),
                    userName.toString(),
                    birthday.toString(),
                    city.toString(),
                    phone.toString()
                ), object : CoroutinesErrorHandler {
                    override fun onError(message: String) {
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    }
                })
        }
    }

    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val inputStream: InputStream? = null
                try {
                    val data = it.data
                    val imgUri = data?.data
                    val inputStream =
                        imgUri?.let { uri -> context?.contentResolver?.openInputStream(uri) }
                    val yourSelectedImage: Bitmap = BitmapFactory.decodeStream(inputStream)
                    selectedImage.setImageBitmap(yourSelectedImage)
                    encode(yourSelectedImage)
                } catch (e: FileNotFoundException) {
                    e.stackTrace
                }
            }
        }

    fun encode(image: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        val imageEncoded = Base64.encodeToString(b, Base64.DEFAULT)
        Log.e("LOOK", imageEncoded)
        return imageEncoded
    }
}