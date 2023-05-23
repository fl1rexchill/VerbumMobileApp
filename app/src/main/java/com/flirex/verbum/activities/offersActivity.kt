package com.flirex.verbum.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.flirex.verbum.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class offersActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    private lateinit var offersBackButton: ImageButton
    private lateinit var offersActivityTitle: TextView
    private lateinit var offersNumberOne: TextView
    private lateinit var offersNumberTwo: TextView
    private lateinit var offersNumberThree: TextView

    var offersOneString:String?=""
    var offersTwoString:String?=""
    var offersThreeString:String?=""

    var newDefinitionsString:String?=""
    lateinit var newDefinitionsList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offers)

        offersBackButton = findViewById(R.id.offersBackButton)
        offersBackButton.setBackgroundResource(0)

        offersActivityTitle = findViewById(R.id.offersActivityTitle)
        offersNumberOne = findViewById(R.id.offersNumberOne)
        offersNumberTwo = findViewById(R.id.offersNumberTwo)
        offersNumberThree = findViewById(R.id.offersNumberThree)

        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore

        findViewById<ImageButton>(R.id.offersBackButton)
            .setOnClickListener {
                this.finish()
            }

        db.collection("definitions").document("newDefinitions")
            .get()
            .addOnSuccessListener { document ->
                newDefinitionsString = document.getString("allName")
                offersOneString = document.getString("def1")
                offersTwoString = document.getString("def2")
                offersThreeString = document.getString("def3")
                newDefinitionsList = newDefinitionsString!!.split(" ") as MutableList<String>
                if (newDefinitionsList.size == 1) {
                    offersNumberOne.setText(newDefinitionsList[0])
                    findViewById<TextView>(R.id.offersNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, AloneNewWordActivity::class.java)
                            intent.putExtra("word", "1")
                            startActivity(intent)
                        }
                }
                if (newDefinitionsList.size == 2) {
                    offersNumberOne.setText(newDefinitionsList[0])
                    offersNumberTwo.setText(newDefinitionsList[1])
                    findViewById<TextView>(R.id.offersNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, AloneNewWordActivity::class.java)
                            intent.putExtra("word", "1")
                            startActivity(intent)
                        }
                    findViewById<TextView>(R.id.offersNumberTwo)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, AloneNewWordActivity::class.java)
                            intent.putExtra("word", "2")
                            startActivity(intent)
                        }
                }
                if (newDefinitionsList.size == 3) {
                    offersNumberOne.setText(newDefinitionsList[0])
                    offersNumberTwo.setText(newDefinitionsList[1])
                    offersNumberThree.setText(newDefinitionsList[2])
                    findViewById<TextView>(R.id.offersNumberOne)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, AloneNewWordActivity::class.java)
                            intent.putExtra("word", "1")
                            startActivity(intent)
                        }
                    findViewById<TextView>(R.id.offersNumberTwo)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, AloneNewWordActivity::class.java)
                            intent.putExtra("word", "2")
                            startActivity(intent)
                        }
                    findViewById<TextView>(R.id.offersNumberThree)
                        .setOnClickListener {
                            val intent: Intent = Intent(this, AloneNewWordActivity::class.java)
                            intent.putExtra("word", "3")
                            startActivity(intent)
                        }
                }
            }
    }
}