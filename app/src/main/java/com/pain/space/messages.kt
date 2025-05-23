package com.pain.space

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pain.space.model.User
import com.pain.space.recyclerViews_and_adapter.messageUserAdapter

class messages : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_messages)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView: RecyclerView = findViewById(R.id.firend_list)
        val alone : ImageView = findViewById(R.id.alone)

        val userList = listOf(
            User("Prashant Kumar"),
            User("Aryan Raj"),
            User("Nikita Sharma")
        )

        if(userList.size > 0){
            alone.visibility = View.GONE
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = messageUserAdapter(userList)
    }
}