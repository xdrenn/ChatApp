package com.example.chatapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.adapters.ChatsAdapter
import com.example.chatapp.databinding.FragmentChatBinding
import com.example.chatapp.databinding.FragmentChatsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatsFragment : Fragment() {

    private var _binding: FragmentChatsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatsBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.rcView

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ChatsAdapter(fillList())

    }

    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        data.add("Last message")
        return data
    }
}