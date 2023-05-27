package com.example.chatapp.adapters

import android.annotation.SuppressLint
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R

class ChatsAdapter(private val chats: List<String>, private val onItemClicked: (position: Int) -> Unit) :
    RecyclerView.Adapter<ChatsAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val messageTextView: TextView = itemView.findViewById(R.id.tv_chats_message)
        val userNameTextView: TextView = itemView.findViewById(R.id.user_name)

        override fun onClick(v: View) {
            val position = bindingAdapterPosition
            onItemClicked(position)
        }
        init {
            itemView.setOnClickListener(this)
        }
    }






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.users_item, parent, false)
        return MyViewHolder(itemView, onItemClicked)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.messageTextView.text = chats[position]
        holder.userNameTextView.text = "User"

    }
    override fun getItemCount() = chats.size

}

