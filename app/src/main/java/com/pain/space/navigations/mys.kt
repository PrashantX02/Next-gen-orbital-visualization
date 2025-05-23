package com.pain.space.navigations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.pain.space.R
import com.pain.space.model.mys_card
import com.pain.space.recyclerViews_and_adapter.deck_card_adapter

class mys : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view : View =  inflater.inflate(R.layout.fragment_mys, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.deck_recycler)
        val items = listOf(mys_card("Prashant Kumar Das | E X P E R T"),mys_card("Prashant Kumar Das | E X P E R T"),mys_card("Prashant Kumar Das | E X P E R T"),mys_card("Prashant Kumar Das | E X P E R T"))

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = deck_card_adapter(items)

        return view
    }

    override fun onDestroy() {
        //context?.let { Animatoo.animateSlideLeft(it) }
        super.onDestroy()
    }
}