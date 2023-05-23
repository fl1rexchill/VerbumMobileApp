package com.flirex.verbum.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.addCallback
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
var checkStatus:String? = ""

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    private lateinit var textInput2: TextInputEditText
    private lateinit var newOffersButtonMain: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textInput2 = findViewById(R.id.text_input2)
        newOffersButtonMain = findViewById(R.id.newOffersButtonMain)
        newOffersButtonMain.visibility = View.GONE
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        db.collection("users").document(currentUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                checkStatus = document.getString("status")
                playlistsString = document.getString("playlists")
                playlistNumberOne = document.getString("playlist1")
                playlistNumberTwo = document.getString("playlist2")
                playlistNumberThree = document.getString("playlist3")
                PlaylistsList = playlistsString!!.split(" ") as MutableList<String>
                if (checkStatus == "1"){
                    newOffersButtonMain.visibility = View.VISIBLE
                }
            }
        findViewById<Button>(R.id.find_button2)
            .setOnClickListener {
                val intent: Intent = Intent(this, DescriptionActivity::class.java )
                intent.putExtra("word", textInput2.text.toString())
                intent.putExtra("numPlaylist", "0")
                startActivity(intent)
            }
        findViewById<Button>(R.id.playlistsButtonMain)
            .setOnClickListener {
                val intent: Intent = Intent(this, PlaylistsActivity::class.java )
                startActivity(intent)
            }
        findViewById<Button>(R.id.alphabetButtonMain)
            .setOnClickListener {
                val intent: Intent = Intent(this, MainWindowActivity::class.java )
                startActivity(intent)
            }
        findViewById<Button>(R.id.newWordButtonMain)
            .setOnClickListener {
                val intent: Intent = Intent(this, NewWordActivity::class.java )
                startActivity(intent)
            }
        findViewById<Button>(R.id.newOffersButtonMain)
            .setOnClickListener {
                val intent: Intent = Intent(this, offersActivity::class.java )
                startActivity(intent)
            }

//        onBackPressedDispatcher.addCallback(this /* lifecycle owner */){
//            //for back arrow handle
//
//        }
    }
}