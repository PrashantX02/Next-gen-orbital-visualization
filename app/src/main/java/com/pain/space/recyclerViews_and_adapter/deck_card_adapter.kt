package com.pain.space.recyclerViews_and_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pain.space.R
import com.pain.space.model.mys_card

class deck_card_adapter(val list : List<mys_card>) : RecyclerView.Adapter<deck_card_adapter.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.deck_card,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var name : TextView = holder.itemView.findViewById(R.id.name_deck_owner)
        name.text = list.get(position).name
    }
}