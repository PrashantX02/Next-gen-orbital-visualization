package com.pain.space

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pain.space.model.explore_item

class ExploreAdapter(private val exploreList: List<explore_item>) :
    RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder>() {

    class ExploreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView8)
        val titleTextView: TextView = itemView.findViewById(R.id.text1)// N E B U L A
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.universe_card, parent, false) // Ensure universe_card.xml matches the layout
        return ExploreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        val exploreItem = exploreList[position]

        holder.imageView.setImageResource(exploreItem.imageResId)
        holder.titleTextView.text = exploreItem.title
    }

    override fun getItemCount(): Int = exploreList.size
}
