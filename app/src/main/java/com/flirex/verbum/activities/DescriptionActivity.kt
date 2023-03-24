package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.flirex.verbum.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*


private lateinit var wordText: TextView
private lateinit var definitionText: TextView
private lateinit var descriptionBackButton: ImageButton
private lateinit var add_buttonDescription: Button
private lateinit var LoadingDescription: ProgressBar
private lateinit var playlistNumberOneSwitch: Switch
private lateinit var playlistNumberTwoSwitch: Switch
private lateinit var playlistNumberThreeSwitch: Switch
private lateinit var newPlaylistName: TextInputEditText
private lateinit var createNewPlaylistAnswer: Button
private lateinit var createNewPlaylist: Button



var theDefinition:String? = ""
var theWord:String = ""
var firstLetter = Char
var userPlaylists:String? = ""
lateinit var userPlaylistsList: List<String>
var checkplaylists:Int = 1


class DescriptionActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    private var resultIntent: Intent? = null
    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes! it were deprecated?
            val data: Intent? = result.data
            if (data != null) {
                resultIntent = data
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        wordText = findViewById(R.id.word_text)
        wordText.visibility = View.GONE

        definitionText = findViewById(R.id.definition_text)
        definitionText.visibility = View.GONE

        descriptionBackButton = findViewById(R.id.descriptionBackButton)
        descriptionBackButton.visibility = View.GONE
        descriptionBackButton.setBackgroundResource(0)

        add_buttonDescription = findViewById(R.id.add_buttonDescription)
        add_buttonDescription.visibility = View.GONE

        LoadingDescription = findViewById(R.id.LoadingDescription)
        LoadingDescription.visibility = View.VISIBLE

        playlistNumberOneSwitch = findViewById(R.id.playlistSwitch1)
        playlistNumberOneSwitch.visibility = View.GONE

        playlistNumberTwoSwitch = findViewById(R.id.playlistSwitch2)
        playlistNumberTwoSwitch.visibility = View.GONE

        playlistNumberThreeSwitch = findViewById(R.id.playlistSwitch3)
        playlistNumberThreeSwitch.visibility = View.GONE

        newPlaylistName = findViewById(R.id.namePlaylistTextInput)
        newPlaylistName.visibility = View.GONE

        createNewPlaylistAnswer = findViewById(R.id.createNewPlaylistAnswerButton)
        createNewPlaylistAnswer.visibility = View.GONE

        createNewPlaylist = findViewById(R.id.createNewPlaylistButton)
        createNewPlaylist.visibility = View.GONE


        theWord = intent?.extras?.getString("word").toString()
        theWord = theWord.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        theWord = theWord.replace(" ", "")
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        db.collection("${theWord.get(0)}").document("$theWord")
            .get()
            .addOnSuccessListener { document ->
                theDefinition = document.getString("definition")
                wordText.setText(theWord)
                definitionText.setText(theDefinition)
                LoadingDescription.visibility = View.GONE
                descriptionBackButton.visibility = View.VISIBLE
                add_buttonDescription.visibility = View.VISIBLE
                wordText.visibility = View.VISIBLE
                definitionText.visibility = View.VISIBLE
            }
        db.collection("users").document(currentUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                userPlaylists = document.getString("playlists")
                if (userPlaylists == ""){
                    checkplaylists = 0
                }else{
                    userPlaylistsList = userPlaylists!!.split(" ") as MutableList<String>
                    checkplaylists = 1
                }
                Log.d("test", "$userPlaylists")
                Log.d("test", "$userPlaylistsList")
            }
        findViewById<ImageButton>(R.id.descriptionBackButton)
            .setOnClickListener {
                val childIntent: Intent = Intent(this, MainWindowActivity::class.java )
                resultLauncher.launch(childIntent)
            }
        findViewById<Button>(R.id.add_buttonDescription)
            .setOnClickListener {
                add_buttonDescription.visibility = View.GONE
                if (checkplaylists == 0){
                    playlistNumberOneSwitch.visibility = View.GONE
                    playlistNumberTwoSwitch.visibility = View.GONE
                    playlistNumberThreeSwitch.visibility = View.GONE
                    createNewPlaylist.visibility = View.VISIBLE
                }else {
                    try {
                        if (userPlaylistsList.size == 1) {
                            playlistNumberOneSwitch.visibility = View.VISIBLE
                            playlistNumberOneSwitch.setText(userPlaylistsList[0])
                            playlistNumberTwoSwitch.visibility = View.GONE
                            playlistNumberThreeSwitch.visibility = View.GONE
                            createNewPlaylist.visibility = View.VISIBLE
                            Log.d("test", "correct")
                        }
                        if (userPlaylistsList.size == 2) {
                            playlistNumberOneSwitch.visibility = View.VISIBLE
                            playlistNumberTwoSwitch.visibility = View.VISIBLE
                            playlistNumberThreeSwitch.visibility = View.GONE
                            createNewPlaylist.visibility = View.VISIBLE
                        }
                        if (userPlaylistsList.size == 3) {
                            playlistNumberOneSwitch.visibility = View.VISIBLE
                            playlistNumberTwoSwitch.visibility = View.VISIBLE
                            playlistNumberThreeSwitch.visibility = View.VISIBLE
                            createNewPlaylist.visibility = View.VISIBLE
                        }
                    }catch (e:Exception){
                        Log.d("test", "not correct")
                    }
                }
            }
        findViewById<Button>(R.id.createNewPlaylistButton)
            .setOnClickListener {
                createNewPlaylist.visibility = View.GONE
                createNewPlaylistAnswer.visibility = View.VISIBLE
                newPlaylistName.visibility = View.VISIBLE
            }
    }

    /*fun addToPlaylistFromChoose() {
        val childIntent: Intent
        childIntent = Intent(this, ChoosePlaylistAct::class.java )
        resultLauncher.launch(childIntent)
    }*/


}

