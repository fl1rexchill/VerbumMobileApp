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
import com.google.firebase.firestore.AggregateSource
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*


private lateinit var playlistsActivityTitle: TextView
private lateinit var playlistNumberOneActivityPlaylist: TextView
private lateinit var playlistNumberTwoActivityPlaylist: TextView
private lateinit var playlistNumberThreeActivityPlaylist: TextView
private lateinit var noPlaylistNowText: TextView
private lateinit var descriptionBackButton: ImageButton
private lateinit var PlaylistTextInput: TextInputEditText
private lateinit var createNewPlaylistAnswerButtonPlaylist: Button
private lateinit var createNewPlaylistButton: Button
const val DEFAULT_PLAYLIST_STATUS = "private"

class PlaylistsActivity : AppCompatActivity(), View.OnClickListener {

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

        PlaylistTextInput = findViewById(R.id.PlaylistTextInput)
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

        //Functions usage
        createNewPlaylistButton.setOnClickListener{
            //adding of playlist
            addPlaylist("Hentai","Aaaah",mutableListOf("yamete","kudasai","senpai"),
                "Anonymous Developer","VERY PUBLIC, READING THIS IS NECESSARY"){
                id -> Log.d("ADDED", id)
                handleWordsFromPlaylist(id) { words ->
                    //recieving of words
                    Log.d("WORDS IN ARE", words.toString())
                }
            }

        }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    fun addPlaylist(
        title   : String,
        about   : String = "",
        words   : MutableList<String> = mutableListOf(),
        author  : String = currentUser!!.uid,
        status  : String = DEFAULT_PLAYLIST_STATUS,
        callback: ((String) -> Unit)? = null
    ){
        //TODO("a lot of successfulity checks")
        var id: String
        db.collection("playlists")
            .count()
            .get(AggregateSource.SERVER)
            .addOnCompleteListener { task ->
                id = task.result.count.toString()

                db.collection("playlists")
                    .document(id)
                    .set(Playlist(
                        id,
                        author,
                        title,
                        about,
                        status,
                        words
                    ))
                    .addOnCompleteListener {
                        callback?.invoke(id)
                    }
            }
    }

    fun handleWordsFromPlaylist(id:String, handleFun: (List<String>) -> Unit){

        db.collection("playlists")
            .document(id)
            .get().addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("words GET", "DocumentSnapshot data: ${document.data}")
                    handleFun(document.get("words")!! as List<String>)
                } else {
                    Log.d("EEEError", "No such document")
                }
            }
    }
}