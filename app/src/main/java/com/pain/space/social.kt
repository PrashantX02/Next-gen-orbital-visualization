package com.pain.space

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.pain.space.navigations.frnd
import com.pain.space.navigations.mys
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation
import me.ibrahimsn.lib.SmoothBottomBar

class social : AppCompatActivity() {

    private lateinit var bottomBar: SmoothBottomBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_social)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bottomBar = findViewById(R.id.bottomBar)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, mys())
            .commit()

        bottomBar.setOnItemSelectedListener { index ->
            val selectedFragment = when (index) {
                0 -> mys()
                1 -> frnd()
                else -> mys()
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, selectedFragment)
                .commit()
            true
        }

    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        super.onBackPressed()
        Animatoo.animateSlideRight(this)
    }
}