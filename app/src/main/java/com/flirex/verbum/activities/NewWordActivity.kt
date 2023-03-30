package com.flirex.verbum.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.flirex.verbum.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class NewWordActivity : AppCompatActivity() {

    private lateinit var text_input3: TextInputEditText
    private lateinit var multiTextNewWord: TextInputEditText
    private lateinit var newWordBackButton: ImageButton

    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    lateinit var allNameList: MutableList<String>

    var allNameString:String? = ""
    var def1String:String? = ""
    var def2String:String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        text_input3 = findViewById(R.id.text_input3)
        multiTextNewWord = findViewById(R.id.multiTextNewWord)

        newWordBackButton = findViewById(R.id.newWordBackButton)
        newWordBackButton.setBackgroundResource(0)

        findViewById<ImageButton>(R.id.newWordBackButton)
            .setOnClickListener {
                val intent: Intent = Intent(this, MainActivity::class.java )
                startActivity(intent)
            }
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        findViewById<Button>(R.id.pushNewWordButton)
            .setOnClickListener {
                db.collection("definitions").document("newDefinitions")
                    .get()
                    .addOnSuccessListener { document ->
                        allNameString = document.getString("allName")
                        def1String = document.getString("def1")
                        def2String = document.getString("def2")
                        if (allNameString == "") {
                            val newDefDoc = newDefinitions(
                                allName = text_input3.text.toString(),
                                def1 = multiTextNewWord.text.toString(),
                                def2 = "",
                                def3 = ""
                            )
                            val washingtonRef = db.collection("definitions").document("newDefinitions")
                            washingtonRef
                                .set(newDefDoc)
                        }else {
                            allNameList = allNameString!!.split(" ") as MutableList<String>
                            if (allNameList.size == 1) {
                                val newDefDoc = newDefinitions(
                                    allName = allNameString + " " + text_input3.text.toString(),
                                    def1 = def1String,
                                    def2 = multiTextNewWord.text.toString(),
                                    def3 = ""
                                )
                                val washingtonRef =
                                    db.collection("definitions").document("newDefinitions")
                                washingtonRef
                                    .set(newDefDoc)
                            }
                            if (allNameList.size == 2) {
                                val newDefDoc = newDefinitions(
                                    allName = allNameString + " " + text_input3.text.toString(),
                                    def1 = def1String,
                                    def2 = def2String,
                                    def3 = multiTextNewWord.text.toString(),
                                )
                                val washingtonRef =
                                    db.collection("definitions").document("newDefinitions")
                                washingtonRef
                                    .set(newDefDoc)
                            }
                        }

                    }
                val intent: Intent = Intent(this, MainActivity::class.java )
                startActivity(intent)
            }
    }
}