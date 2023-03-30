package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.flirex.verbum.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoadingActivity : Activity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

//        init auth
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if(currentUser != null){
            val intent: Intent = Intent(this, MainActivity::class.java )
            startActivity(intent)

            return
        }

        val intent: Intent = Intent(this, SingUpActivity::class.java )
        startActivity(intent)
    }
}