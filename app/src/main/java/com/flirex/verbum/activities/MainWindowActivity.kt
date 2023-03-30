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
                val intent: Intent = Intent(this, MainActivity::class.java )
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter1)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "1")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter2)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "2")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter3)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "3")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter4)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "4")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter5)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "5")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter6)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "6")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter7)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "7")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter8)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "8")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter9)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "9")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter10)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "10")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter11)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "11")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter12)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "12")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter13)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "13")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter14)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "14")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter15)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "15")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter16)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "16")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter17)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "17")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter18)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "18")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter19)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "19")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter20)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "20")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter21)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "21")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter22)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "22")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter23)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "23")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter24)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "24")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter25)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "25")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter26)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "26")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter27)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "27")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter28)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "28")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter29)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "29")
                startActivity(intent)
            }
        findViewById<ImageView>(R.id.imageViewLetter30)
            .setOnClickListener {
                val intent: Intent = Intent(this, ChooseWordFromLetterActivity::class.java )
                intent.putExtra("letter", "30")
                startActivity(intent)
            }



    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}