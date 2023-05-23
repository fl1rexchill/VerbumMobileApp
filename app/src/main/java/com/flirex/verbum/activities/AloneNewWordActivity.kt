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

class AloneNewWordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    lateinit var textInputAloneNewWord:TextInputEditText
    lateinit var multiTextNewWordAlone:TextInputEditText
    lateinit var correctAnswerAloneNewWord: Button
    lateinit var aloneNewWordBackButton: ImageButton

    var newDefinitionNumber:String? = ""
    var defStringAlone:String? = ""
    var newDefinitionsString:String? = ""
    lateinit var newDefinitionsList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alone_new_word)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore

        textInputAloneNewWord = findViewById(R.id.textInputAloneNewWord)

        multiTextNewWordAlone = findViewById(R.id.multiTextNewWordAlone)

        newDefinitionNumber = intent?.extras?.getString("word").toString()

        db.collection("definitions").document("newDefinitions")
            .get()
            .addOnSuccessListener { document ->
                newDefinitionsString = document.getString("allName")
                newDefinitionsList = newDefinitionsString!!.split(" ") as MutableList<String>
                if (newDefinitionNumber == "1"){
                    defStringAlone = document.getString("def1")
                    multiTextNewWordAlone.setText(defStringAlone)
                    textInputAloneNewWord.setText(newDefinitionsList[0])
                }
                if (newDefinitionNumber == "2"){
                    defStringAlone = document.getString("def2")
                    multiTextNewWordAlone.setText(defStringAlone)
                    textInputAloneNewWord.setText(newDefinitionsList[1])
                }
                if (newDefinitionNumber == "3"){
                    defStringAlone = document.getString("def3")
                    multiTextNewWordAlone.setText(defStringAlone)
                    textInputAloneNewWord.setText(newDefinitionsList[2])
                }
            }

        correctAnswerAloneNewWord = findViewById(R.id.correctAnswerAloneNewWord)
        findViewById<Button>(R.id.correctAnswerAloneNewWord)
            .setOnClickListener {
                TODO("sending definition")
            }

        aloneNewWordBackButton = findViewById(R.id.aloneNewWordBackButton)
        aloneNewWordBackButton.setBackgroundResource(0)



        findViewById<ImageButton>(R.id.aloneNewWordBackButton)
            .setOnClickListener {
                this.finish()
            }
    }
}