package com.pain.space.sheet_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pain.space.R
import com.pain.space.model.Comment
import com.pain.space.recyclerViews_and_adapter.CommentAdapter

class comment_sheetFragment() : BottomSheetDialogFragment() {

    lateinit var comment_recyclerView : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view : View = inflater.inflate(R.layout.fragment_comment_sheet, container, false)

        comment_recyclerView = view.findViewById(R.id.comments)

        var list = listOf(
            Comment("Prashant Kumar | 5h","","This is is Insane !!",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
            Comment("Prashant Kumar | 5h","","This is is Insane !!",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
            Comment("Prashant Kumar | 5h","","This is is Insane !!",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
            Comment("Prashant Kumar | 5h","","This is is Insane !!",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
            Comment("Prashant Kumar | 5h","","This is is Insane !!",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
            Comment("Prashant Kumar | 5h","","This is is Insane !!",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),
        )

        val adapter_comment  = CommentAdapter(list)
        comment_recyclerView.layoutManager = LinearLayoutManager(requireContext())
        comment_recyclerView.adapter = adapter_comment

        return view;
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}