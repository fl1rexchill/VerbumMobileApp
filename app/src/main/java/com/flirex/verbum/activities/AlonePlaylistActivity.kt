package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.flirex.verbum.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var alonePlaylistActivityTitle: TextView

var playListNameAlonePlaylistActivity:String?=""
var numberOfPlaylist:String? = ""
lateinit var wordsFromPlayList: MutableList<String>

class AlonePlaylistActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    var playlistnameresfromintent: Intent? = null
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes! it were deprecated?
            val data: Intent? = result.data
            if (data != null) {
                playlistnameresfromintent = data
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        playListNameAlonePlaylistActivity = intent?.extras?.getString("word").toString()
        numberOfPlaylist= intent?.extras?.getString("num").toString()
        Log.d("test", "$playListNameAlonePlaylistActivity")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alone_playlist)
        alonePlaylistActivityTitle = findViewById(R.id.alonePlaylistActivityTitle)
        alonePlaylistActivityTitle.setText(playListNameAlonePlaylistActivity)
        if (numberOfPlaylist == "1"){
            db.collection("users").document(currentUser!!.uid)
                .get()
                .addOnSuccessListener { document ->
                    playlistNumberOne = document.getString("playlist1")
                    wordsFromPlayList = playlistNumberOne!!.split(" ") as MutableList<String>
                }
        }
        if (numberOfPlaylist == "2"){

        }
        if (numberOfPlaylist == "3"){

        }
    }
}