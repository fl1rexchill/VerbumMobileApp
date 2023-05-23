package com.flirex.verbum.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.flirex.verbum.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainWindowActivity: AppCompatActivity(), View.OnClickListener{
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    private lateinit var findButton: Button
    private lateinit var textInput: TextInputEditText
    private lateinit var AlphabetBackButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alphabet)
        findButton = findViewById(R.id.find_button)
        textInput = findViewById(R.id.text_input)
        AlphabetBackButton = findViewById(R.id.AlphabetBackButton)
        AlphabetBackButton.setBackgroundResource(0)
        findButton.setOnClickListener(this)
        findViewById<Button>(R.id.find_button)
            .setOnClickListener {
                val intent: Intent = Intent(this, DescriptionActivity::class.java )
                intent.putExtra("word", textInput.text.toString())
                intent.putExtra("numPlaylist", "0")
                startActivity(intent)
            }
        findViewById<ImageButton>(R.id.AlphabetBackButton)
            .setOnClickListener {
                this.finish()
            }

        for (i in 1..30){
            findViewById<ImageView>(resources.getIdentifier(
                "imageViewLetter$i",
                "id",
                packageName
            )).setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "$i")
                startActivity(intent)
            }
        }


    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}