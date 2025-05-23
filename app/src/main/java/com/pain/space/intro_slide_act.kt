package com.pain.space

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.pain.space.Dashboard.DashBoard

class intro_slide_act : AppCompatActivity() {

    private var isZoomed = false
    private var currentZoomedView: ImageView? = null
    private lateinit var play : ImageView
    private var pause = true
    private var selected_planet = 0 ;
    private lateinit var media : MediaPlayer

    private lateinit var id : TextView
    private lateinit var name : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro_slide)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        id = findViewById<TextView>(R.id.id_card)
        name = findViewById<TextView>(R.id.name)
        val explore = findViewById<TextView>(R.id.textView10)
        val toDashBoard = findViewById<TextView>(R.id.to_dashboard)
        val id = findViewById<TextView>(R.id.id_card)
        val name = findViewById<TextView>(R.id.name)



        play = findViewById(R.id.play)
        media = MediaPlayer.create(this,R.raw.sun_sound)
        play.setOnClickListener {
            if(pause){
                media?.start()
                pause = false
                play.setImageResource(R.drawable.pause)
            }else{
                media?.pause()
                play.setImageResource(R.drawable.play)
                pause = true
            }

        }
        media.setOnCompletionListener{
            play.setImageResource(R.drawable.play)
        }

        toDashBoard.setOnClickListener {
            media.pause()
            val intent = Intent(this,DashBoard::class.java)
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
        }
        explore.setOnClickListener {
            media.pause()
            val intent = Intent(this,MainActivity2::class.java)
            Log.d("planet", "onCreate: $selected_planet")
            intent.putExtra("key","$selected_planet")
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
        }

        val planets = listOf(
            findViewById<ImageView>(R.id.venus),
            findViewById<ImageView>(R.id.mercury),
            findViewById<ImageView>(R.id.earth),
            findViewById<ImageView>(R.id.mars),
            findViewById<ImageView>(R.id.jupitar),
            findViewById<ImageView>(R.id.saturn),
            findViewById<ImageView>(R.id.neptune),
            findViewById<ImageView>(R.id.uranus),
            findViewById<ImageView>(R.id.pluto)
        )

        for (planet in planets) {
            planet.setOnClickListener {
                if (isZoomed && currentZoomedView == planet) {
                    resetZoom(planet)
                } else {
                    zoomToPlanet(planet)
                }
            }
        }
    }

    private fun zoomToPlanet(planet: ImageView) {
        // Reset previous zoomed planet
        currentZoomedView?.let { resetZoom(it) }

        var scaleFactor = if (planet.id == R.id.pluto || planet.id == R.id.venus) 4f else 2f
        val temp = scaleFactor
        if(planet.id == R.id.jupitar || planet.id == R.id.saturn) scaleFactor = 1.2f

        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, scaleFactor)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, scaleFactor)

        ObjectAnimator.ofPropertyValuesHolder(planet, scaleX, scaleY).apply {
            duration = 500 // Smooth animation
            start()
        }

        selected_planet = planet.id

        play.setImageResource(R.drawable.pause)

        if (R.id.jupitar == selected_planet) {
            media.pause()
            media.release()
            name.text = "JUPITER"
            id.text = "Largest planet, gas giant"
            media = MediaPlayer.create(this,R.raw.jupiter_mp)
            media.isLooping = true
            media.start()
        } else if (R.id.mars == selected_planet) {
            media.pause()
            media.release()
            name.text = "MARS"
            id.text = "Red, dusty, cold, small"
            media = MediaPlayer.create(this,R.raw.mars_mp)
            media.isLooping = true
            media.start()
        } else if (R.id.earth == selected_planet) {
            media.pause()
            media.release()
            name.text = "EARTH"
            id.text = "Life, water, one moon"
            media = MediaPlayer.create(this,R.raw.earth_mp)
            media.isLooping = true
            media.start()
        } else if (R.id.venus == selected_planet) {
            media.pause()
            media.release()
            name.text = "VENUS"
            id.text = "Hot, toxic, thick clouds"
            media = MediaPlayer.create(this,R.raw.venus_mp)
            media.isLooping = true
            media.start()
        } else if (R.id.mercury == selected_planet) {
            media.pause()
            media.release()
            name.text = "MERCURY"
            id.text = "Smallest, no atmosphere"
            media = MediaPlayer.create(this,R.raw.murc_sound)
            media.isLooping = true
            media.start()
        } else if (R.id.uranus == selected_planet) {
            media.pause()
            media.release()
            name.text = "URANUS"
            id.text = "Sideways spin, icy giant"
            media = MediaPlayer.create(this,R.raw.uranus_mp)
            media.isLooping = true
            media.start()
        } else if (R.id.neptune == selected_planet) {
            media.pause()
            media.release()
            name.text = "NEPTUNE"
            id.text = "Cold, windy, blue gas"
            media = MediaPlayer.create(this,R.raw.neptune)
            media.isLooping = true
            media.start()
        } else if (R.id.pluto == selected_planet) {
            media.pause()
            media.release()
            name.text = "PLUTO"
            id.text = "Dwarf, icy, eccentric orbit"
            media = MediaPlayer.create(this,R.raw.pluto_mp)
            media.isLooping = true
            media.start()
        } else if (R.id.saturn == selected_planet) {
            media.pause()
            media.release()
            name.text = "SATURN"
            id.text = "Iconic rings, gas giant"
            media = MediaPlayer.create(this,R.raw.saturn_mp)
            media.isLooping = true
            media.start()
        } else {
            media.pause()
            media.release()
            name.text = "SUN"
            id.text = "G-type main-sequence star"
            media = MediaPlayer.create(this,R.raw.sun_sound)
            media.isLooping = true
            media.start()
        }



        currentZoomedView = planet
        isZoomed = true
    }

    private fun resetZoom(planet: ImageView) {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f) // Back to normal
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f)

        ObjectAnimator.ofPropertyValuesHolder(planet, scaleX, scaleY).apply {
            duration = 500
            start()
        }

        selected_planet = 0

        currentZoomedView = null
        isZoomed = false
    }
}