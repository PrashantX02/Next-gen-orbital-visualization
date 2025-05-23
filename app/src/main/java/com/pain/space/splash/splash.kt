package com.pain.space.splash

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.pain.space.Auth.login
import com.pain.space.MainActivity2
import com.pain.space.R
import com.pain.space.intro_slide_act
import com.pain.space.social

class splash : AppCompatActivity() {

    private lateinit var load: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        load = findViewById(R.id.splash_animation)
        load.setAnimation("loading_bar.json")
        load.playAnimation()
        load.repeatCount = 0

        val intent  = Intent(this,login::class.java)


        load.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                load.pauseAnimation()
                startActivity(intent)
                slide_left()
                finish()
            }

            override fun onAnimationCancel(animation: Animator) {
                // Called when the animation is canceled
            }

            override fun onAnimationRepeat(animation: Animator) {
                // Called when the animation repeats
            }
        })

    }

    public fun slide_left(){
        Animatoo.animateSlideLeft(this)
    }
}