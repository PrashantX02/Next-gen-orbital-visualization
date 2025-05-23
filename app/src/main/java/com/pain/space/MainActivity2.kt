package com.pain.space

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import org.w3c.dom.Text

class MainActivity2 : AppCompatActivity() {

    private lateinit var planet : ImageView

    private var key = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val value = intent.getStringExtra("key")
        planet = findViewById(R.id.imageView2)


        if("${R.id.jupitar}".equals(value)){
            planet.setImageResource(R.drawable.jupitar)
            planet.scaleX = 2.2f
            planet.scaleY = 2.2f
            key = 5
        }else if ("${R.id.mars}".equals(value)){
            key = 4
            planet.setImageResource(R.drawable.mars)
        }else if ("${R.id.earth}".equals(value)){
            planet.setImageResource(R.drawable.earth)
            planet.scaleX = 1.2f
            planet.scaleY = 1.2f
            key = 3
        }else if ("${R.id.venus}".equals(value)){
            planet.setImageResource(R.drawable.venus)
            key = 1
        }else if("${R.id.mercury}".equals(value)){
            planet.setImageResource(R.drawable.mercury)
            planet.scaleX = 1.1f
            planet.scaleY = 1.1f
            key = 2
        }else if("${R.id.uranus}".equals(value)){
            planet.setImageResource(R.drawable.uranus)
            planet.scaleX = 1.1f
            planet.scaleY = 1.1f
            key = 7
        }else if("${R.id.neptune}".equals(value)){
            planet.setImageResource(R.drawable.neptune)
            planet.scaleX = 1.2f
            planet.scaleY = 1.2f
            key = 8
        } else if("${R.id.pluto}".equals(value)){
            planet.setImageResource(R.drawable.pluto)
            planet.scaleX = 1.0f
            planet.scaleY = 1.0f
            key = 9
        }else if("${R.id.saturn}".equals(value)){
            planet.setImageResource(R.drawable.saturn)
            planet.scaleX = 1.5f
            planet.scaleY = 1.5f
            key = 6
        }else{
            planet.setImageResource(R.drawable.sun)
            planet.scaleX = 1.2f
            planet.scaleY = 1.2f
            key = 0;
        }

        val vis : TextView = findViewById(R.id.textView);

        vis.setOnClickListener{
            val intent  = Intent(this,MainActivity::class.java)
            val s : String = "${key}"
            intent.putExtra("key",s);
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Animatoo.animateSlideRight(this)
    }
}
