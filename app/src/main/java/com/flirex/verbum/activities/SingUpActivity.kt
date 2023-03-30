package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.widget.doOnTextChanged
import com.flirex.verbum.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SingUpActivity : Activity() {
    private lateinit var auth: FirebaseAuth
    private val store = Firebase.firestore
    val db = Firebase.firestore

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var continueButton: Button
    private lateinit var continueProgressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)
        //        init auth
        auth = Firebase.auth

//        init views
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        continueButton = findViewById(R.id.continueButton)
        continueProgressBar = findViewById(R.id.continueProgressBar)

//        handle inputs' text changes
        emailEditText.doOnTextChanged { _, _, _, _ -> handleInputState() }
        passwordEditText.doOnTextChanged { _, _, _, _ -> handleInputState() }

//        handle button clicks
        continueButton.setOnClickListener { sendRequest() }

//        init button state with single function call
        handleInputState()
    }
    private fun handleInputState() {
//        handle button state to avoid pressing without filled inputs
        if(emailEditText.text.isNotBlank() && passwordEditText.text.isNotBlank()) {
            continueButton.isClickable = true
            continueButton.alpha = 1f
        }
        else {
            continueButton.isClickable = false
            continueButton.alpha = 0.3f
        }
    }

    private fun sendRequest() {
//        change button state to Loading
        val currentUser = auth.currentUser
        continueButton.isClickable = false
        continueButton.text = ""
        continueButton.alpha = 0.3f
        continueProgressBar.visibility = View.VISIBLE

//        get inputs' values
        var email = emailEditText.text.toString()
        var password = passwordEditText.text.toString()

//        send sign-up request
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
//                cleanup variables with sensetive data to avoid it saving in memory
                email = ""
                password = ""

//                handle result of auth
                handleResult(task.isSuccessful)
            }
    }

    private fun handleResult(isSuccess: Boolean) {
//        get current user
        val currentUser = auth.currentUser

        if(isSuccess && currentUser != null) {
//            Авторизация также может не пройти, если пароль оказался
//            слишком слабым. Это определяет сам Firebase

//            cleanup fields to avoid saving data in memory
            emailEditText.setText("")
            passwordEditText.setText("")

            var checkBoxUser: CheckBox = findViewById(R.id.checkBoxUser)
            var checkBoxModer: CheckBox = findViewById(R.id.checkBoxModer)

            var statusString:String = "0"
            if(checkBoxUser.isChecked) statusString = "0"
            if(checkBoxModer.isChecked) statusString = "1"

//            init user document
            val userDoc = User(
                uid = currentUser.uid,
                email = currentUser.email,
                name = currentUser.displayName,
                status = statusString,
                playlists = "",
                playlist1 = "",
                playlist2 = "",
                playlist3 = "",
                letters = "Баснописец Ёмкость Абитуриент Абонемент Абсурдный Ангар Беззаконие Внаём Вражда Глагол Говеть Драгунка Европеизм Жмот Знаменование Икона Йог Ксении Лебедь Мракобес Новосёл Овёс Птица Рыбак Созорничать Тона Указ Филёр Хетты Циклопы Чизель Шибер Щипцы Эндемики Юрта Ялик"
            )
            /*db.collection("users")
                .add(userDoc)
                .addOnSuccessListener { documentReference ->
                    Log.d("test","$documentReference")
                }
                .addOnFailureListener { e ->
                    Log.w("test", "Error adding document", e)
                }*/

//            save user document to firestore
            store.collection("users")
                .document(currentUser.uid)
                .set(userDoc)
                .addOnCompleteListener { task ->
//                    notify that saving document failed
                    if (!task.isSuccessful) {
                        Toast.makeText(this, "Failed to Save User Document", Toast.LENGTH_SHORT).show()
                    }



                    val intent: Intent = Intent(this, MainActivity::class.java )
                    startActivity(intent)

                }

            return
        }


//        notify that sign-up failed
        Toast.makeText(this, "Failed to Sign Up", Toast.LENGTH_SHORT).show()
//        return button state to initial
        continueButton.isClickable = true
        continueButton.text = "Sign Up"
        continueButton.alpha = 1f
        continueProgressBar.visibility = View.GONE
    }
}