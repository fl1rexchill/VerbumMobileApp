package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.flirex.verbum.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var alonePlaylistActivityTitle: TextView
private lateinit var alonePlaylistsBackButton: ImageButton
private lateinit var wordNumberOne: TextView
private lateinit var wordNumberTwo: TextView
private lateinit var wordNumberThree: TextView

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

        wordNumberOne = findViewById(R.id.wordNumberOne)
        wordNumberTwo = findViewById(R.id.wordNumberTwo)
        wordNumberThree = findViewById(R.id.wordNumberThree)

        alonePlaylistsBackButton = findViewById(R.id.alonePlaylistsBackButton)
        alonePlaylistsBackButton.setBackgroundResource(0)

        alonePlaylistActivityTitle = findViewById(R.id.alonePlaylistActivityTitle)
        alonePlaylistActivityTitle.setText(playListNameAlonePlaylistActivity)

        Log.d("test", "$numberOfPlaylist ")
        if (numberOfPlaylist == "1"){
            db.collection("users").document(currentUser!!.uid)
                .get()
                .addOnSuccessListener { document ->
                    playlistNumberOne = document.getString("playlist1")
                    wordsFromPlayList = playlistNumberOne!!.split(" ") as MutableList<String>
                    try {
                        wordNumberOne.setText(wordsFromPlayList[0])
                        findViewById<TextView>(R.id.wordNumberOne)
                            .setOnClickListener {
                                val intent: Intent = Intent(this, DescriptionActivity::class.java )
                                intent.putExtra("word", wordsFromPlayList[0])
                                intent.putExtra("numPlaylist", "1")
                                startActivity(intent)
                            }
                    }catch (e:Exception){
                        Log.d("test", "no Word num one")
                    }
                    try {
                        wordNumberTwo.setText(wordsFromPlayList[1])
                        findViewById<TextView>(R.id.wordNumberTwo)
                            .setOnClickListener {
                                val intent: Intent = Intent(this, DescriptionActivity::class.java )
                                intent.putExtra("word", wordsFromPlayList[1])
                                intent.putExtra("numPlaylist", "1")
                                startActivity(intent)
                            }
                    }catch (e:Exception){
                        Log.d("test", "no Word num two")
                    }
                    try {
                        wordNumberThree.setText(wordsFromPlayList[2])
                        findViewById<TextView>(R.id.wordNumberThree)
                            .setOnClickListener {
                                val intent: Intent = Intent(this, DescriptionActivity::class.java )
                                intent.putExtra("word", wordsFromPlayList[2])
                                intent.putExtra("numPlaylist", "1")
                                startActivity(intent)
                            }
                    }catch (e:Exception){
                        Log.d("test", "no Word num three")
                    }
                }
        }
        if (numberOfPlaylist == "2"){
            db.collection("users").document(currentUser!!.uid)
                .get()
                .addOnSuccessListener { document ->
                    playlistNumberTwo = document.getString("playlist2")
                    wordsFromPlayList = playlistNumberTwo!!.split(" ") as MutableList<String>
                    try {
                        wordNumberOne.setText(wordsFromPlayList[0])
                        findViewById<TextView>(R.id.wordNumberOne)
                            .setOnClickListener {
                                val intent: Intent = Intent(this, DescriptionActivity::class.java )
                                intent.putExtra("word", wordsFromPlayList[0])
                                intent.putExtra("numPlaylist", "2")
                                startActivity(intent)
                            }
                    }catch (e:Exception){
                        Log.d("test", "no Word num one")
                    }
                    try {
                        wordNumberTwo.setText(wordsFromPlayList[1])
                        findViewById<TextView>(R.id.wordNumberTwo)
                            .setOnClickListener {
                                val intent: Intent = Intent(this, DescriptionActivity::class.java )
                                intent.putExtra("word", wordsFromPlayList[1])
                                intent.putExtra("numPlaylist", "2")
                                startActivity(intent)
                            }
                    }catch (e:Exception){
                        Log.d("test", "no Word num two")
                    }
                    try {
                        wordNumberThree.setText(wordsFromPlayList[2])
                        findViewById<TextView>(R.id.wordNumberThree)
                            .setOnClickListener {
                                val intent: Intent = Intent(this, DescriptionActivity::class.java )
                                intent.putExtra("word", wordsFromPlayList[2])
                                intent.putExtra("numPlaylist", "2")
                                startActivity(intent)
                            }
                    }catch (e:Exception){
                        Log.d("test", "no Word num three")
                    }
                }
        }
        if (numberOfPlaylist == "3"){
            db.collection("users").document(currentUser!!.uid)
                .get()
                .addOnSuccessListener { document ->
                    playlistNumberThree = document.getString("playlist3")
                    wordsFromPlayList = playlistNumberThree!!.split(" ") as MutableList<String>
                    try {
                        wordNumberOne.setText(wordsFromPlayList[0])
                        findViewById<TextView>(R.id.wordNumberOne)
                            .setOnClickListener {
                                val intent: Intent = Intent(this, DescriptionActivity::class.java )
                                intent.putExtra("word", wordsFromPlayList[0])
                                intent.putExtra("numPlaylist", "3")
                                startActivity(intent)
                            }
                    }catch (e:Exception){
                        Log.d("test", "no Word num one")
                    }
                    try {
                        wordNumberTwo.setText(wordsFromPlayList[1])
                        findViewById<TextView>(R.id.wordNumberTwo)
                            .setOnClickListener {
                                val intent: Intent = Intent(this, DescriptionActivity::class.java )
                                intent.putExtra("word", wordsFromPlayList[1])
                                intent.putExtra("numPlaylist", "3")
                                startActivity(intent)
                            }
                    }catch (e:Exception){
                        Log.d("test", "no Word num two")
                    }
                    try {
                        wordNumberThree.setText(wordsFromPlayList[2])
                        findViewById<TextView>(R.id.wordNumberThree)
                            .setOnClickListener {
                                val intent: Intent = Intent(this, DescriptionActivity::class.java )
                                intent.putExtra("word", wordsFromPlayList[2])
                                intent.putExtra("numPlaylist", "3")
                                startActivity(intent)
                            }
                    }catch (e:Exception){
                        Log.d("test", "no Word num three")
                    }
                }
        }
        findViewById<ImageButton>(R.id.alonePlaylistsBackButton)
            .setOnClickListener {
                finish()
            }
    }
}