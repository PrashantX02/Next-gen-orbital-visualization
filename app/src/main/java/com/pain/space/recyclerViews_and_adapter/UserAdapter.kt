package com.pain.space.recyclerViews_and_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pain.space.R
import com.pain.space.model.notification_item

class UserAdapter(private val items: List<notification_item>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.textView)
        val checkImage: ImageView = itemView.findViewById(R.id.imageView21)
        val crossImage: ImageView = itemView.findViewById(R.id.imageView21) // Or separate ID if needed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.friend_card, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = items[position]
        holder.nameText.text = item.name
        holder.checkImage.visibility = if (item.isChecked) View.VISIBLE else View.GONE
        holder.crossImage.visibility = if (!item.isChecked) View.VISIBLE else View.GONE
    }
}
