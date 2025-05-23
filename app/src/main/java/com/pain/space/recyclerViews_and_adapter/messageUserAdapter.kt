package com.pain.space.recyclerViews_and_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pain.space.R
import com.pain.space.model.User


class messageUserAdapter(private val users: List<User>) : RecyclerView.Adapter<messageUserAdapter.MessageUserViewHolder>() {

    inner class MessageUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.name)
        val avatar: ImageView = itemView.findViewById(R.id.imageView32)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_list_item, parent, false)
        return MessageUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: messageUserAdapter.MessageUserViewHolder, position: Int) {
        val user = users[position]
        holder.nameText.text = user.name
    }

    override fun getItemCount(): Int = users.size
}
