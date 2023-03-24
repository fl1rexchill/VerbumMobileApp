package com.flirex.verbum.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.flirex.verbum.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChooseWordFromLetterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_word_from_letter)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        db.collection("${theWord.get(0)}").document("$theWord")
            .get()
            .addOnSuccessListener { document ->
                Log.d("test", "$document")
                theDefinition = document.getString("definition")
            }
    }
}