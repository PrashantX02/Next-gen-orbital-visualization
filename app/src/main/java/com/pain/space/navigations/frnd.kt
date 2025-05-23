package com.pain.space.navigations

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.pain.space.R
import com.pain.space.model.notification_item
import com.pain.space.model.suggestions_item
import com.pain.space.recyclerViews_and_adapter.UserAdapter
import com.pain.space.recyclerViews_and_adapter.suggestion_adapter
import com.qamar.curvedbottomnaviagtion.log
import org.w3c.dom.Text

class frnd : Fragment() {

    private lateinit var displayList : MutableList<notification_item>
    private lateinit var frnrcc : RecyclerView
    private lateinit var user_adapter : UserAdapter
    private var isExpanded = false

    private lateinit var displayList2 : MutableList<suggestions_item>
    private lateinit var suggestion_rec : RecyclerView
    private lateinit var suggestion_adapter : suggestion_adapter
    private var isExpanded2 = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View =  inflater.inflate(R.layout.fragment_frnd, container, false)

        frnrcc = view.findViewById(R.id.recyclerView)
        suggestion_rec = view.findViewById(R.id.recyclerView2)

        val btnShowMore2 : TextView = view.findViewById(R.id.textView)
        val btnShowMore : TextView = view.findViewById(R.id.textView23)

        var fullList : List<notification_item> = listOf(
            notification_item("Prashant Kumar | M A S T E R", true),
            notification_item("Rohit Singh | G U E S T", false),
            notification_item("Anita Sharma | M O D", true),
            notification_item("Dinesh Mehta | G U E S T", false))

        var fullList2 = ArrayList<suggestions_item>()


        FirebaseDatabase.getInstance().reference.child("user").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                fullList2.clear()
              for(snap in snapshot.children){
                  val uid = snap.key
                  val name = snap.getValue(String::class.java)
                  if(!uid!!.equals(FirebaseAuth.getInstance().currentUser!!.uid)){
                      Log.d("suggestion", "onDataChange: ${name}")
                      fullList2.add(suggestions_item(name.toString(),false))
                  }
              }
                displayList2.clear()
                displayList2 = fullList2.take(3).toMutableList()
                suggestion_adapter = suggestion_adapter(displayList2)
                suggestion_rec.layoutManager = LinearLayoutManager(context)
                suggestion_rec.adapter = suggestion_adapter

                suggestion_adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        displayList = fullList.take(3).toMutableList()
        displayList2 = fullList2.take(3).toMutableList()

        user_adapter = UserAdapter(displayList)
        frnrcc.layoutManager = LinearLayoutManager(context)
        frnrcc.adapter = user_adapter

        suggestion_adapter = suggestion_adapter(displayList2)
        suggestion_rec.layoutManager = LinearLayoutManager(context)
        suggestion_rec.adapter = suggestion_adapter

        btnShowMore.setOnClickListener {
            isExpanded = !isExpanded
            if (isExpanded) {
                displayList.clear()
                displayList.addAll(fullList)
                btnShowMore.text = "Show Less"
            } else {
                displayList.clear()
                displayList.addAll(fullList.take(3))
                btnShowMore.text = "Show More"
            }
            user_adapter.notifyDataSetChanged()
        }

        btnShowMore2.setOnClickListener {
            isExpanded2 = !isExpanded2
            if (isExpanded2) {
                displayList2.clear()
                displayList2.addAll(fullList2)
                btnShowMore2.text = "Show Less"
            } else {
                displayList2.clear()
                displayList2.addAll(fullList2.take(3))
                btnShowMore2.text = "Show More"
            }
            suggestion_adapter.notifyDataSetChanged()
        }
        return view;
    }
}