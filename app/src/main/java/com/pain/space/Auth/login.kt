package com.pain.space.Auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.pain.space.R
import com.pain.space.intro_slide_act
import kotlin.math.log

class login : AppCompatActivity() {

    private lateinit var regis : TextView
    private lateinit var login: TextView
    private lateinit var email: EditText
    private lateinit var passcode: EditText
    private lateinit var root : ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(FirebaseAuth.getInstance().currentUser != null){
            val intent = Intent(this,intro_slide_act::class.java)
            startActivity(intent)
            finish()
        }

        root = findViewById(R.id.main)

        regis = findViewById(R.id.register)
        login = findViewById(R.id.textView16)
        email = findViewById(R.id.editText)
        passcode = findViewById(R.id.editText2)



        login.setOnClickListener {
            var em = email.text.toString()
            var ps = passcode.text.toString()
            if(em.isNotEmpty() && ps.isNotEmpty()){
                root.alpha = 0.5f
                val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_progress, null)
                val progressDialog = AlertDialog.Builder(this)
                    .setView(dialogView)
                    .setCancelable(false)
                    .create()
                progressDialog.show()

                FirebaseAuth.getInstance().signInWithEmailAndPassword(em,ps).addOnCompleteListener(){ task->
                    if(task.isSuccessful){
                        root.alpha = 1f
                        progressDialog.dismiss()
                        showSnackbar("Login Successful")
                        val intent = Intent(this,intro_slide_act::class.java)
                        startActivity(intent)
                        Animatoo.animateSlideLeft(this)
                    }else{
                        showSnackbar("Login Failed")
                    }
                }
            }
        }

        regis.setOnClickListener {
            val intent = Intent(this,register::class.java)
            startActivity(intent)
            Animatoo.animateSlideLeft(this)
        }

    }

    private fun showSnackbar(message: String) {
        Log.d("auth", "${message}")
        Snackbar.make(root, message, Snackbar.LENGTH_LONG).show()
    }
}