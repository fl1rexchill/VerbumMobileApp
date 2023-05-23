package com.flirex.verbum.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.flirex.verbum.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


private lateinit var playlistsActivityTitle: TextView
private lateinit var playlistNumberOneActivityPlaylist: TextView
private lateinit var playlistNumberTwoActivityPlaylist: TextView
private lateinit var playlistNumberThreeActivityPlaylist: TextView
private lateinit var noPlaylistNowText: TextView
private lateinit var descriptionBackButton: ImageButton
private lateinit var PlaylistTextInput: TextInputEditText
private lateinit var createNewPlaylistAnswerButtonPlaylist: Button

class PlaylistsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

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

        PlaylistTextInput = findViewById(R.id.PlaylistTextInput)
        PlaylistTextInput.visibility = View.GONE

        createNewPlaylistAnswerButtonPlaylist = findViewById(R.id.createNewPlaylistAnswerButtonPlaylist)
        createNewPlaylistAnswerButtonPlaylist.visibility = View.GONE

        //playlistsActivityTitle.setText("Плейлисты")
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
        if (PlaylistsList.size == 0) {
            noPlaylistNowText.visibility = View.VISIBLE
        }
        if (PlaylistsList.size == 1) {
            playlistNumberOneActivityPlaylist.visibility = View.VISIBLE
            playlistNumberOneActivityPlaylist.setText(PlaylistsList[0])
        }
        if (PlaylistsList.size == 2) {
            playlistNumberOneActivityPlaylist.visibility = View.VISIBLE
            playlistNumberOneActivityPlaylist.setText(PlaylistsList[0])
            playlistNumberTwoActivityPlaylist.visibility = View.VISIBLE
            playlistNumberTwoActivityPlaylist.setText(PlaylistsList[1])
        }
        if (PlaylistsList.size == 3) {
            playlistNumberOneActivityPlaylist.visibility = View.VISIBLE
            playlistNumberOneActivityPlaylist.setText(PlaylistsList[0])
            playlistNumberTwoActivityPlaylist.visibility = View.VISIBLE
            playlistNumberTwoActivityPlaylist.setText(PlaylistsList[1])
            playlistNumberThreeActivityPlaylist.visibility = View.VISIBLE
            playlistNumberThreeActivityPlaylist.setText(PlaylistsList[2])
        }
        findViewById<ImageButton>(R.id.playlistsBackButton)
            .setOnClickListener {
                this.finish()
            }
        playlistNumberOneActivityPlaylist.setOnClickListener(this)
        findViewById<TextView>(R.id.playlistNumberOneActivityPlaylist)
            .setOnClickListener {
                val intent: Intent = Intent(this, AlonePlaylistActivity::class.java )
                intent.putExtra("word", PlaylistsList[0])
                intent.putExtra("num", "1")
                startActivity(intent)
            }
        findViewById<TextView>(R.id.playlistNumberTwoActivityPlaylist)
            .setOnClickListener {
                val intent: Intent = Intent(this, AlonePlaylistActivity::class.java )
                intent.putExtra("word", PlaylistsList[1])
                intent.putExtra("num", "2")
                startActivity(intent)
            }
        findViewById<TextView>(R.id.playlistNumberThreeActivityPlaylist)
            .setOnClickListener {
                val intent: Intent = Intent(this, AlonePlaylistActivity::class.java )
                intent.putExtra("word", PlaylistsList[2])
                intent.putExtra("num", "3")
                startActivity(intent)
            }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}