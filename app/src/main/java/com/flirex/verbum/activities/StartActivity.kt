package com.flirex.verbum.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.flirex.verbum.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class StartActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        //db.collection("users").document(currentUser!!.uid)
        //    .get()

        val intent = Intent(this, LoadingActivity::class.java)
        val handler = android.os.Handler()
        handler.postDelayed({ startActivity(intent) }, 2000)
    }
}