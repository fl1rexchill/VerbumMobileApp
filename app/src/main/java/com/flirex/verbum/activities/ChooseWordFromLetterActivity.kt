package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.flirex.verbum.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var chooseWordNumberOne: TextView
private lateinit var chooseWordNumberTwo: TextView
private lateinit var chooseWordNumberThree: TextView
private lateinit var chooseWordNumberFour: TextView
private lateinit var chooseWordActivityTitle: TextView
private lateinit var chooseWordBackButton: ImageButton


class ChooseWordFromLetterActivity : AppCompatActivity() {
    var letterString:String? = ""
    var theWorsStringOnChooseActivity:String? = ""
    lateinit var theWorsStringOnChooseActivityList: MutableList<String>
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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_word_from_letter)
        chooseWordBackButton = findViewById(R.id.chooseWordBackButton)
        chooseWordBackButton.setBackgroundResource(0)

        chooseWordActivityTitle = findViewById(R.id.chooseWordActivityTitle)

        chooseWordNumberOne = findViewById(R.id.chooseWordNumberOne)
        chooseWordNumberTwo = findViewById(R.id.chooseWordNumberTwo)
        chooseWordNumberThree = findViewById(R.id.chooseWordNumberThree)
        chooseWordNumberFour = findViewById(R.id.chooseWordNumberFour)

        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        letterString = intent?.extras?.getString("letter").toString()
        if (letterString == "1") {
            chooseWordActivityTitle.setText("А")
            db.collection("А").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList = theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    chooseWordNumberTwo.setText(theWorsStringOnChooseActivityList[1])
                    chooseWordNumberThree.setText(theWorsStringOnChooseActivityList[2])
                    chooseWordNumberFour.setText(theWorsStringOnChooseActivityList[3])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java )
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "1")
                            startActivity(intent)
                        }
                    findViewById<TextView>(R.id.chooseWordNumberTwo)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java )
                            intent.putExtra("word", theWorsStringOnChooseActivityList[1])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "1")
                            startActivity(intent)
                        }
                    findViewById<TextView>(R.id.chooseWordNumberThree)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java )
                            intent.putExtra("word", theWorsStringOnChooseActivityList[2])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "1")
                            startActivity(intent)
                        }
                    findViewById<TextView>(R.id.chooseWordNumberFour)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java )
                            intent.putExtra("word", theWorsStringOnChooseActivityList[3])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "1")
                            startActivity(intent)
                        }
                }
        }
        if (letterString == "2") {
            chooseWordActivityTitle.setText("Б")
            db.collection("Б").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    chooseWordNumberTwo.setText(theWorsStringOnChooseActivityList[1])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "2")
                            startActivity(intent)
                        }
                    findViewById<TextView>(R.id.chooseWordNumberTwo)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[1])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "2")
                            startActivity(intent)
                        }
                }
        }

        if (letterString == "3") {
            chooseWordActivityTitle.setText("В")
            db.collection("В").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    chooseWordNumberTwo.setText(theWorsStringOnChooseActivityList[1])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "3")
                            startActivity(intent)
                        }
                    findViewById<TextView>(R.id.chooseWordNumberTwo)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[1])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "3")
                            startActivity(intent)
                        }
                }
        }
        if (letterString == "4") {
            chooseWordActivityTitle.setText("Г")
            db.collection("Г").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    chooseWordNumberTwo.setText(theWorsStringOnChooseActivityList[1])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "4")
                            startActivity(intent)
                        }
                    findViewById<TextView>(R.id.chooseWordNumberTwo)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[1])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "4")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "5") {
            chooseWordActivityTitle.setText("Д")
            db.collection("Д").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "5")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "6") {
            chooseWordActivityTitle.setText("Е")
            db.collection("Е").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "6")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "7") {
            chooseWordActivityTitle.setText("Ё")
            db.collection("Ё").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "7")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "8") {
            chooseWordActivityTitle.setText("Ж")
            db.collection("Ж").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "8")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "9") {
            chooseWordActivityTitle.setText("З")
            db.collection("З").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "9")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "10") {
            chooseWordActivityTitle.setText("И")
            db.collection("И").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "10")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "11") {
            chooseWordActivityTitle.setText("Й")
            db.collection("Й").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "11")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "12") {
            chooseWordActivityTitle.setText("К")
            db.collection("К").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "12")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "13") {
            chooseWordActivityTitle.setText("Л")
            db.collection("Л").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "13")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "14") {
            chooseWordActivityTitle.setText("М")
            db.collection("М").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "14")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "15") {
            chooseWordActivityTitle.setText("Н")
            db.collection("Н").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "15")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "16") {
            chooseWordActivityTitle.setText("О")
            db.collection("О").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "16")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "17") {
            chooseWordActivityTitle.setText("П")
            db.collection("П").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "17")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "18") {
            chooseWordActivityTitle.setText("Р")
            db.collection("Р").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "18")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "19") {
            chooseWordActivityTitle.setText("С")
            db.collection("С").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "19")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "20") {
            chooseWordActivityTitle.setText("Т")
            db.collection("Т").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "20")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "21") {
            chooseWordActivityTitle.setText("У")
            db.collection("У").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "21")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "22") {
            chooseWordActivityTitle.setText("Ф")
            db.collection("Ф").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "22")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "23") {
            chooseWordActivityTitle.setText("Х")
            db.collection("Х").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "23")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "24") {
            chooseWordActivityTitle.setText("Ц")
            db.collection("Ц").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "24")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "25") {
            chooseWordActivityTitle.setText("Ч")
            db.collection("Ч").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "25")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "26") {
            chooseWordActivityTitle.setText("Ш")
            db.collection("Ш").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "26")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "27") {
            chooseWordActivityTitle.setText("Щ")
            db.collection("Щ").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "27")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "28") {
            chooseWordActivityTitle.setText("Э")
            db.collection("Э").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "28")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "29") {
            chooseWordActivityTitle.setText("Ю")
            db.collection("Ю").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "29")
                            startActivity(intent)
                        }
                }

        }
        if (letterString == "30") {
            chooseWordActivityTitle.setText("Я")
            db.collection("Я").document("definitions")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    theWorsStringOnChooseActivity = document.getString("all")
                    theWorsStringOnChooseActivityList =
                        theWorsStringOnChooseActivity!!.split(" ") as MutableList<String>
                    Log.d("test", "$theWorsStringOnChooseActivityList")
                    chooseWordNumberOne.setText(theWorsStringOnChooseActivityList[0])
                    findViewById<TextView>(R.id.chooseWordNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, DescriptionActivity::class.java)
                            intent.putExtra("word", theWorsStringOnChooseActivityList[0])
                            intent.putExtra("numPlaylist", "0")
                            intent.putExtra("letter", "30")
                            startActivity(intent)
                        }
                }

        }
        findViewById<ImageButton>(R.id.chooseWordBackButton)
            .setOnClickListener {
                this.finish()
            }
    }
}