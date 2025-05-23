package com.pain.space.Auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
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

class register : AppCompatActivity() {

    private lateinit var verify : TextView
    private lateinit var name : EditText
    private lateinit var email : EditText
    private lateinit var cpassCode : EditText
    private lateinit var passcode : EditText
    private lateinit var auth : FirebaseAuth
    private lateinit var ref : FirebaseDatabase

    private lateinit var rootLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance()


        rootLayout = findViewById(R.id.main)

        verify = findViewById(R.id.textView16)
        name = findViewById(R.id.name)
        email = findViewById(R.id.editText)
        cpassCode = findViewById(R.id.editText3)
        passcode = findViewById(R.id.editText2);

        verify.setOnClickListener {

            var p = passcode.text.toString()
            var c = cpassCode.text.toString()
            var nm = name.text.toString()
            var em = email.text.toString()

            if(p.equals(c) && !p.isEmpty() && !nm.isEmpty() && !em.isEmpty()){
                rootLayout.alpha = 0.5f
                val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_progress, null)
                val progressDialog = AlertDialog.Builder(this)
                    .setView(dialogView)
                    .setCancelable(false)
                    .create()
                progressDialog.show()
                auth.createUserWithEmailAndPassword(em,p).addOnCompleteListener{task->
                    if(task.isSuccessful){
                        progressDialog.dismiss()
                        rootLayout.alpha = 1f
                        ref.getReference().child("user").child(auth.currentUser!!.uid).setValue(nm)
                        sendVerificationEmail()
                    }
                }

            }
        }
    }

    private fun sendVerificationEmail() {
        val user = auth.currentUser
        user?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                showSnackbar("Verification email sent. Please check your inbox.")
                auth.signOut()
                finish()
                Animatoo.animateSlideRight(this)
            } else {
               showSnackbar("sendVerificationEmail: Failed to send verification email.")
            }
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(rootLayout, message, Snackbar.LENGTH_LONG).show()
        Log.d("auth", "${message}")
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}