package com.pain.space.recyclerViews_and_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pain.space.R
import com.pain.space.model.notification_item
import com.pain.space.model.suggestions_item

class suggestion_adapter (private val items: List<suggestions_item>) :
    RecyclerView.Adapter<suggestion_adapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
          val nameText: TextView = itemView.findViewById(R.id.textView)
//        val checkImage: ImageView = itemView.findViewById(R.id.imageView21)
//        val crossImage: ImageView = itemView.findViewById(R.id.imageView21) // Or separate ID if needed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.suggestions_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameText.text = item.name
    }
}