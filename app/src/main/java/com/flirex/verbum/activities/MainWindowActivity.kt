package com.flirex.verbum.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import com.flirex.verbum.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
var playlistsString:String? = ""
var playlistNumberOne:String? = ""
var playlistNumberTwo:String? = ""
var playlistNumberThree:String? = ""
lateinit var PlaylistsList: MutableList<String>

class MainWindowActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    private lateinit var findButton: Button
    private lateinit var textInput: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_window)
        findButton = findViewById(R.id.find_button)
        textInput = findViewById(R.id.text_input)
        findButton.setOnClickListener(this)
        findViewById<Button>(R.id.find_button)
            .setOnClickListener {
                val intent: Intent = Intent(this, DescriptionActivity::class.java )
                intent.putExtra("word", textInput.text.toString())
                startActivity(intent)
            }
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        db.collection("users").document(currentUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                playlistsString = document.getString("playlists")
                playlistNumberOne = document.getString("playlist1")
                playlistNumberTwo = document.getString("playlist2")
                playlistNumberThree = document.getString("playlist3")
                PlaylistsList = playlistsString!!.split(" ") as MutableList<String>
            }
        findViewById<Button>(R.id.myPlaylistsButton)
            .setOnClickListener {
                val intent: Intent = Intent(this, PlaylistsActivity::class.java )
                startActivity(intent)
            }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}