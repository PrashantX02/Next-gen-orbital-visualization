package com.pain.space.sheet_fragment

import android.icu.util.LocaleData
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.core.Constants
import com.pain.space.R
import com.pain.space.model.Comment
import com.pain.space.recyclerViews_and_adapter.CommentAdapter
import com.qamar.curvedbottomnaviagtion.log
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Formatter

class comment_sheetFragment(var key_default_watch: String) : BottomSheetDialogFragment() {

    lateinit var comment_recyclerView : RecyclerView
    lateinit var comment_edit_text : EditText
    lateinit var comment_sent : ImageView
    lateinit var key : String
    lateinit var root : ConstraintLayout

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val uid : String = FirebaseAuth.getInstance().currentUser!!.uid

        var view : View = inflater.inflate(R.layout.fragment_comment_sheet, container, false)

        comment_recyclerView = view.findViewById(R.id.comments)
        comment_edit_text = view.findViewById(R.id.editText4)
        comment_sent = view.findViewById(R.id.sent_comment)
        root = view.findViewById(R.id.bottom_up_root)

        key = key_default_watch

        comment_sent.setOnClickListener {
            var str = comment_edit_text.text.toString()
            val current = LocalDateTime.now()
            val format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val formatted = current.format(format)
            FirebaseDatabase.getInstance().reference.child(key).child("comment").push().setValue(Comment(uid,"Prashant Kumar | 5h",formatted,str,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)).addOnCompleteListener { task->
                if(task.isSuccessful){
                    comment_edit_text.setText("")
                    Snackbar.make(root,"Comment posted",Snackbar.LENGTH_SHORT).show()
                }else{
                    Log.d("planet", "failed posting  comment")
                }
            }
        }

        var list = listOf(
            Comment(uid,"Prashant Kumar | 5h","","This is is Insane !!",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)
        )

        FirebaseDatabase.getInstance().reference.child(key).child("comment").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(snap in snapshot.children){
                        Log.d("planet", "Snapshot: ${snap.value}")
                        val cl = snap.getValue(Comment::class.java)

                        if(cl != null) Log.d("planet", "names " + cl.username)
                        else Log.d("planet", "names" + null)
                    }
                }else Log.d("planet", "data" + null)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        val adapter_comment  = CommentAdapter(list)
        comment_recyclerView.layoutManager = LinearLayoutManager(requireContext())
        comment_recyclerView.adapter = adapter_comment

        return view;
    }




    override fun onDestroy() {
        super.onDestroy()
    }
}