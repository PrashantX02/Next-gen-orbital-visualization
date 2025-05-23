package com.pain.space.Dashboard

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.pain.space.ExploreAdapter
import com.pain.space.R
import com.pain.space.horror_space
import com.pain.space.messages
import com.pain.space.model.explore_item
import com.pain.space.social

class DashBoard : AppCompatActivity() {

    private lateinit var explore : TextView
    private lateinit var message : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dash_board)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        explore  = findViewById(R.id.explore_horror)
        message = findViewById(R.id.imageView10)

        message.setOnClickListener {
            val intent = Intent(this,messages::class.java)
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
        }

        explore.setOnClickListener {
            val intent = Intent(this,horror_space::class.java)
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
        }
        val social_img = findViewById<ImageView>(R.id.imageView9)

        social_img.setOnClickListener {
            val intent = Intent(this,social::class.java)
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
        }

        var ai = findViewById<LottieAnimationView>(R.id.Ai)
        ai.setAnimation("ai.json")
        ai.playAnimation()
        //ai.repeatCount = 0

        val exploreList = listOf(
            explore_item(R.drawable.nebula, "N E B U L A"),
            explore_item(R.drawable.black_hole_img, "E V E N T  H O R I Z O N"),
            explore_item(R.drawable.milkyway, "A N D R O M E D A"),
           // explore_item(R.drawable.magnetar, "M A G N E T A R")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.universe_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = ExploreAdapter(exploreList)

    }
}