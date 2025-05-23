package com.pain.space

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.blogspot.atifsoftwares.animatoolib.Animatoo

class horror_space : AppCompatActivity() {

    private lateinit var area51 : ConstraintLayout
    private lateinit var planetX : ConstraintLayout
    private lateinit var roswell : ConstraintLayout
    private lateinit var knight : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_horror_space)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        area51 = findViewById(R.id.constraintLayout5)
        planetX = findViewById(R.id.constraintLayout6)
        roswell = findViewById(R.id.constraintLayout7)
        knight = findViewById(R.id.imageView28)

        area51.setOnClickListener{
            val intent = Intent(this,swipe_card::class.java)
            intent.putExtra("ev","1")
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
        }

        planetX.setOnClickListener{
            val intent = Intent(this,swipe_card::class.java)
            intent.putExtra("ev","2")
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
        }

        roswell.setOnClickListener{
            val intent = Intent(this,swipe_card::class.java)
            intent.putExtra("ev","3")
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
        }

        knight.setOnClickListener{
            val intent = Intent(this,swipe_card::class.java)
            intent.putExtra("ev","4")
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
        }


    }
}