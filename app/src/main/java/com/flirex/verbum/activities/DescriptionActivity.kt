package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat.postDelayed
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


var statusSting:String? = ""
var theDefinition:String? = ""
var theWord:String = ""
var firstLetter = Char
var userPlaylists:String? = ""
lateinit var userPlaylistsList: List<String>
lateinit var userPlaylistsListNewPlaylist: List<String>
var checkplaylists:Int = 1
var numOfPlaylist:String? = ""
var playListnumOneToPush:String? = ""
var playListnumTwoToPush:String? = ""
var playListnumThreeToPush:String? = ""
var playListnumOneToPushCreate:String? = ""
var playListnumTwoToPushCreate:String? = ""
var playListnumThreeToPushCreate:String? = ""


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

        numOfPlaylist = intent?.extras?.getString("numPlaylist").toString()
        if (numOfPlaylist == "1"){
            playlistNumberOneSwitch.isChecked = true
        }
        if (numOfPlaylist == "2"){
            playlistNumberTwoSwitch.isChecked = true
        }
        if (numOfPlaylist == "3"){
            playlistNumberThreeSwitch.isChecked = true
        }
        theWord = intent?.extras?.getString("word").toString()
        theWord = theWord.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        theWord = theWord.replace(" ", "")
        auth = Firebase.auth
        var currentUser = auth.currentUser
        var db = Firebase.firestore
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
            }
        if (numOfPlaylist == "0") {
            findViewById<ImageButton>(R.id.descriptionBackButton)
                .setOnClickListener {
                    val childIntent: Intent = Intent(this, MainWindowActivity::class.java )
                    resultLauncher.launch(childIntent)
                }
        }
        if (numOfPlaylist == "1"){
            findViewById<ImageButton>(R.id.descriptionBackButton)
                .setOnClickListener {
                    val intent: Intent = Intent(this, AlonePlaylistActivity::class.java )
                    intent.putExtra("word", userPlaylistsList[0])
                    intent.putExtra("num", "1")
                    startActivity(intent)
                }
        }
        if (numOfPlaylist == "2"){
            findViewById<ImageButton>(R.id.descriptionBackButton)
                .setOnClickListener {
                    val intent: Intent = Intent(this, AlonePlaylistActivity::class.java )
                    intent.putExtra("word", userPlaylistsList[1])
                    intent.putExtra("num", "2")
                    startActivity(intent)
                }
        }
        if (numOfPlaylist == "3"){
            findViewById<ImageButton>(R.id.descriptionBackButton)
                .setOnClickListener {
                    val intent: Intent = Intent(this, AlonePlaylistActivity::class.java )
                    intent.putExtra("word", userPlaylistsList[2])
                    intent.putExtra("num", "3")
                    startActivity(intent)
                }
        }
        if (numOfPlaylist == "99"){
            val letterDescription = intent?.extras?.getString("letter").toString()
            findViewById<ImageButton>(R.id.descriptionBackButton)
                .setOnClickListener {
                    val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                    intent.putExtra("letter", "$letterDescription")
                    startActivity(intent)
                }
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
                        Log.d("test", "add_buttonDescription")
                        if (userPlaylistsList.size == 1) {
                            playlistNumberOneSwitch.visibility = View.VISIBLE
                            playlistNumberOneSwitch.setText(userPlaylistsList[0])
                            playlistNumberTwoSwitch.visibility = View.GONE
                            playlistNumberThreeSwitch.visibility = View.GONE
                            createNewPlaylist.visibility = View.VISIBLE
                        }
                        if (userPlaylistsList.size == 2) {
                            playlistNumberOneSwitch.visibility = View.VISIBLE
                            playlistNumberOneSwitch.setText(userPlaylistsList[0])
                            playlistNumberTwoSwitch.visibility = View.VISIBLE
                            playlistNumberTwoSwitch.setText(userPlaylistsList[1])
                            playlistNumberThreeSwitch.visibility = View.GONE
                            createNewPlaylist.visibility = View.VISIBLE
                        }
                        if (userPlaylistsList.size == 3) {
                            playlistNumberOneSwitch.visibility = View.VISIBLE
                            playlistNumberOneSwitch.setText(userPlaylistsList[0])
                            playlistNumberTwoSwitch.visibility = View.VISIBLE
                            playlistNumberTwoSwitch.setText(userPlaylistsList[1])
                            playlistNumberThreeSwitch.visibility = View.VISIBLE
                            playlistNumberThreeSwitch.setText(userPlaylistsList[2])
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
        fun abc(str: String){
            Log.d("test", "$str")
            userPlaylistsListNewPlaylist = str.split(" ") as MutableList<String>
            Log.d("test", "$userPlaylistsListNewPlaylist")
            try {
                if (userPlaylistsListNewPlaylist.size == 1) {
                    playlistNumberOneSwitch.visibility = View.VISIBLE
                    playlistNumberOneSwitch.setText(userPlaylistsListNewPlaylist[0])
                    playlistNumberTwoSwitch.visibility = View.GONE
                    playlistNumberThreeSwitch.visibility = View.GONE
                    createNewPlaylist.visibility = View.VISIBLE
                    Log.d("test", "correct")
                }
                if (userPlaylistsListNewPlaylist.size == 2) {
                    playlistNumberOneSwitch.visibility = View.VISIBLE
                    playlistNumberOneSwitch.setText(userPlaylistsListNewPlaylist[0])
                    playlistNumberTwoSwitch.visibility = View.VISIBLE
                    playlistNumberTwoSwitch.setText(userPlaylistsListNewPlaylist[1])
                    playlistNumberThreeSwitch.visibility = View.GONE
                    createNewPlaylist.visibility = View.VISIBLE
                }
                if (userPlaylistsListNewPlaylist.size == 3) {
                    playlistNumberOneSwitch.visibility = View.VISIBLE
                    playlistNumberOneSwitch.setText(userPlaylistsListNewPlaylist[0])
                    playlistNumberTwoSwitch.visibility = View.VISIBLE
                    playlistNumberTwoSwitch.setText(userPlaylistsListNewPlaylist[1])
                    playlistNumberThreeSwitch.visibility = View.VISIBLE
                    playlistNumberThreeSwitch.setText(userPlaylistsListNewPlaylist[2])
                    createNewPlaylist.visibility = View.VISIBLE
                }
            }catch (e:Exception){
                Log.d("test", "not correct")
            }
        }
        findViewById<Button>(R.id.createNewPlaylistAnswerButton)
            .setOnClickListener {
                createNewPlaylist.visibility = View.VISIBLE
                createNewPlaylistAnswer.visibility = View.GONE
                newPlaylistName.visibility = View.GONE
                db.collection("users").document(currentUser!!.uid)
                    .get()
                    .addOnSuccessListener { document ->
                        userPlaylists = document.getString("playlists")
                        playListnumOneToPushCreate = document.getString("playlist1")
                        playListnumTwoToPushCreate = document.getString("playlist2")
                        playListnumThreeToPushCreate = document.getString("playlist3")
                        statusSting = document.getString("status")
                        userPlaylists = if (userPlaylists == ""){
                            newPlaylistName.text.toString()
                        }else {
                            userPlaylists + " " + newPlaylistName.text.toString()
                        }
                        Log.d("test","$playListnumOneToPushCreate")
                        var userDoc = User(
                            status = statusSting,
                            uid = currentUser.uid,
                            email = currentUser.email,
                            name = currentUser.displayName,
                            playlists = userPlaylists,
                            playlist1 = playListnumOneToPushCreate,
                            playlist2 = playListnumTwoToPushCreate,
                            playlist3 = playListnumThreeToPushCreate,
                            letters = "Баснописец Ёмкость Абитуриент Абонемент Абсурдный Ангар Беззаконие Внаём Вражда Глагол Говеть Драгунка Европеизм Жмот Знаменование Икона Йог Ксении Лебедь Мракобес Новосёл Овёс Птица Рыбак Созорничать Тона Указ Филёр Хетты Циклопы Чизель Шибер Щипцы Эндемики Юрта Ялик"
                        )
                        val washingtonRef = db.collection("users").document(currentUser!!.uid)
                        washingtonRef
                            .set(userDoc)
                        val handler = android.os.Handler()
                        handler.postDelayed({ abc(userPlaylists.toString()) }, 1000)
                    }
            }
        playlistNumberOneSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                db.collection("users").document(currentUser!!.uid)
                    .get()
                    .addOnSuccessListener { document ->
                        userPlaylists = document.getString("playlists")
                        playListnumOneToPush = document.getString("playlist1")
                        playListnumTwoToPush = document.getString("playlist2")
                        playListnumThreeToPush = document.getString("playlist3")
                        statusSting = document.getString("status")
                        playListnumOneToPush = if (playListnumOneToPush == ""){
                            theWord
                        }else {
                            playListnumOneToPush + " " + theWord
                        }
                        val userDoc = User(
                            status = statusSting,
                            uid = currentUser.uid,
                            email = currentUser.email,
                            name = currentUser.displayName,
                            playlists = userPlaylists,
                            playlist1 = playListnumOneToPush,
                            playlist2 = playListnumTwoToPush,
                            playlist3 = playListnumThreeToPush,
                            letters = "Баснописец Ёмкость Абитуриент Абонемент Абсурдный Ангар Беззаконие Внаём Вражда Глагол Говеть Драгунка Европеизм Жмот Знаменование Икона Йог Ксении Лебедь Мракобес Новосёл Овёс Птица Рыбак Созорничать Тона Указ Филёр Хетты Циклопы Чизель Шибер Щипцы Эндемики Юрта Ялик"
                        )
                        val washingtonRef = db.collection("users").document(currentUser!!.uid)
                        washingtonRef
                            .set(userDoc)
                    }
                playListnumOneToPush = ""
                playListnumTwoToPush = ""
                playListnumThreeToPush = ""
            }else{
                println("Standard Switch is off")
            }
        }
        playlistNumberTwoSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                db.collection("users").document(currentUser!!.uid)
                    .get()
                    .addOnSuccessListener { document ->
                        userPlaylists = document.getString("playlists")
                        playListnumOneToPush = document.getString("playlist1")
                        playListnumTwoToPush = document.getString("playlist2")
                        playListnumThreeToPush = document.getString("playlist3")
                        statusSting = document.getString("status")
                        playListnumTwoToPush = if (playListnumTwoToPush == ""){
                            theWord
                        }else {
                            playListnumTwoToPush + " " + theWord
                        }
                        val userDoc = User(
                            status = statusSting,
                            uid = currentUser.uid,
                            email = currentUser.email,
                            name = currentUser.displayName,
                            playlists = userPlaylists,
                            playlist1 = playListnumOneToPush,
                            playlist2 = playListnumTwoToPush,
                            playlist3 = playListnumThreeToPush,
                            letters = "Баснописец Ёмкость Абитуриент Абонемент Абсурдный Ангар Беззаконие Внаём Вражда Глагол Говеть Драгунка Европеизм Жмот Знаменование Икона Йог Ксении Лебедь Мракобес Новосёл Овёс Птица Рыбак Созорничать Тона Указ Филёр Хетты Циклопы Чизель Шибер Щипцы Эндемики Юрта Ялик"
                        )
                        val washingtonRef = db.collection("users").document(currentUser!!.uid)
                        washingtonRef
                            .set(userDoc)
                    }
                playListnumOneToPush = ""
                playListnumTwoToPush = ""
                playListnumThreeToPush = ""
            }else{
                println("Standard Switch is off")
            }
        }
        playlistNumberThreeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                db.collection("users").document(currentUser!!.uid)
                    .get()
                    .addOnSuccessListener { document ->
                        userPlaylists = document.getString("playlists")
                        playListnumOneToPush = document.getString("playlist1")
                        playListnumTwoToPush = document.getString("playlist2")
                        playListnumThreeToPush = document.getString("playlist3")
                        statusSting = document.getString("status")
                        playListnumThreeToPush = if (playListnumThreeToPush == ""){
                            theWord
                        }else {
                            playListnumThreeToPush + " " + theWord
                        }
                        val userDoc = User(
                            status = statusSting,
                            uid = currentUser.uid,
                            email = currentUser.email,
                            name = currentUser.displayName,
                            playlists = userPlaylists,
                            playlist1 = playListnumOneToPush,
                            playlist2 = playListnumTwoToPush,
                            playlist3 = playListnumThreeToPush,
                            letters = "Баснописец Ёмкость Абитуриент Абонемент Абсурдный Ангар Беззаконие Внаём Вражда Глагол Говеть Драгунка Европеизм Жмот Знаменование Икона Йог Ксении Лебедь Мракобес Новосёл Овёс Птица Рыбак Созорничать Тона Указ Филёр Хетты Циклопы Чизель Шибер Щипцы Эндемики Юрта Ялик"
                        )
                        val washingtonRef = db.collection("users").document(currentUser!!.uid)
                        washingtonRef
                            .set(userDoc)
                    }
                playListnumOneToPush = ""
                playListnumTwoToPush = ""
                playListnumThreeToPush = ""
            }else{
                println("Standard Switch is off")
            }
        }
    }


}

