package com.flirex.verbum.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.flirex.verbum.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.AggregateSource
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


private lateinit var playlistsActivityTitle: TextView
private lateinit var playlistNumberOneActivityPlaylist: TextView
private lateinit var playlistNumberTwoActivityPlaylist: TextView
private lateinit var playlistNumberThreeActivityPlaylist: TextView
private lateinit var noPlaylistNowText: TextView
private lateinit var descriptionBackButton: ImageButton
private lateinit var PlaylistTextInput: TextInputEditText
private lateinit var createNewPlaylistAnswerButtonPlaylist: Button
private lateinit var createNewPlaylistButton: Button
const val PRIVATE_PLAYLIST_STATUS = "private"

class PlaylistsActivity : AppCompatActivity(), View.OnClickListener {

    val pm: PlaylistManager = PlaylistManager()

    var auth: FirebaseAuth = Firebase.auth
    val db = Firebase.firestore
    val currentUser = auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlists)
        playlistsActivityTitle = findViewById(R.id.playlistsActivityTitle)

        descriptionBackButton = findViewById(R.id.playlistsBackButton)
        descriptionBackButton.visibility = View.VISIBLE
        descriptionBackButton.setBackgroundResource(0)

        playlistNumberOneActivityPlaylist = findViewById(R.id.playlistNumberOneActivityPlaylist)
        playlistNumberOneActivityPlaylist.visibility = View.GONE

        playlistNumberTwoActivityPlaylist = findViewById(R.id.playlistNumberTwoActivityPlaylist)
        playlistNumberTwoActivityPlaylist.visibility = View.GONE

        playlistNumberThreeActivityPlaylist = findViewById(R.id.playlistNumberThreeActivityPlaylist)
        playlistNumberThreeActivityPlaylist.visibility = View.GONE

        noPlaylistNowText = findViewById(R.id.noPlaylistNowText)
        noPlaylistNowText.visibility = View.GONE

        PlaylistTextInput = findViewById(R.id.playlistTextInput)
        PlaylistTextInput.visibility = View.GONE

        createNewPlaylistAnswerButtonPlaylist = findViewById(R.id.createNewPlaylistAnswerButtonPlaylist)
        createNewPlaylistAnswerButtonPlaylist.visibility = View.GONE

        createNewPlaylistButton = findViewById(R.id.createNewPlaylistButton2)
        //playlistsActivityTitle.setText("Плейлисты")

        db.collection("users").document(currentUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                playlistsString = document.getString("playlists")
                playlistNumberOne = document.getString("playlist1")
                playlistNumberTwo = document.getString("playlist2")
                playlistNumberThree = document.getString("playlist3")
                playlistsList = playlistsString!!.split(",") as MutableList<String>
            }
        //TODO clear this shit
        if (playlistsList.size == 0) {
            noPlaylistNowText.visibility = View.VISIBLE
        }
        if (playlistsList.size == 1) {
            playlistNumberOneActivityPlaylist.visibility = View.VISIBLE
            playlistNumberOneActivityPlaylist.setText(playlistsList[0])
        }
        if (playlistsList.size == 2) {
            playlistNumberOneActivityPlaylist.visibility = View.VISIBLE
            playlistNumberOneActivityPlaylist.setText(playlistsList[0])
            playlistNumberTwoActivityPlaylist.visibility = View.VISIBLE
            playlistNumberTwoActivityPlaylist.setText(playlistsList[1])
        }
        if (playlistsList.size == 3) {
            playlistNumberOneActivityPlaylist.visibility = View.VISIBLE
            playlistNumberOneActivityPlaylist.setText(playlistsList[0])
            playlistNumberTwoActivityPlaylist.visibility = View.VISIBLE
            playlistNumberTwoActivityPlaylist.setText(playlistsList[1])
            playlistNumberThreeActivityPlaylist.visibility = View.VISIBLE
            playlistNumberThreeActivityPlaylist.setText(playlistsList[2])
        }
        findViewById<ImageButton>(R.id.playlistsBackButton)
            .setOnClickListener {
                this.finish()
            }
        playlistNumberOneActivityPlaylist.setOnClickListener(this)
        findViewById<TextView>(R.id.playlistNumberOneActivityPlaylist)
            .setOnClickListener {
                val intent: Intent = Intent(this, AlonePlaylistActivity::class.java )
                intent.putExtra("word", playlistsList[0])
                intent.putExtra("num", "1")
                startActivity(intent)
            }
        findViewById<TextView>(R.id.playlistNumberTwoActivityPlaylist)
            .setOnClickListener {
                val intent: Intent = Intent(this, AlonePlaylistActivity::class.java )
                intent.putExtra("word", playlistsList[1])
                intent.putExtra("num", "2")
                startActivity(intent)
            }
        findViewById<TextView>(R.id.playlistNumberThreeActivityPlaylist)
            .setOnClickListener {
                val intent: Intent = Intent(this, AlonePlaylistActivity::class.java )
                intent.putExtra("word", playlistsList[2])
                intent.putExtra("num", "3")
                startActivity(intent)
            }

        //Functions usage
//        createNewPlaylistButton.setOnClickListener{
//            //adding of playlist
//            addPlaylist("Hentai","Aaaah",mutableListOf("yamete","kudasai","senpai"),
//                "Anonymous Developer","VERY PUBLIC, READING THIS IS NECESSARY"){
//                id -> Log.d("ADDED", id)
//                handleWordsFromPlaylist(id) { words ->
//                    //recieving of words
//                    Log.d("WORDS IN ARE", words.toString())
//                }
//            }
//        }
        createNewPlaylistButton.setOnClickListener{
            //adding of playlist
            lifecycleScope.launch(Dispatchers.IO){
                val id = pm.setPlaylist(
                "2chan",
                "Aaaah",
                mutableListOf("yamete","kudasai","senpai"),
                "Anonymous Developer",
                "VERY PUBLIC, READING THIS IS NECESSARY")

                Log.d("ADDED",id)
                Log.d("GOT", pm.getPlaylist(id).toString())
            }

        }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}