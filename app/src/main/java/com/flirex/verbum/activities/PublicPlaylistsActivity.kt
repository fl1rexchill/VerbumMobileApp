package com.flirex.verbum.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.util.Log
import android.util.Xml
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.flirex.verbum.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.xmlpull.v1.XmlPullParser


class PublicPlaylistsActivity : AppCompatActivity() {

    val pm: PlaylistManager = PlaylistManager()

    var auth: FirebaseAuth = Firebase.auth
    val db = Firebase.firestore
    val currentUser = auth.currentUser
    private lateinit var plList: LinearLayout
    private lateinit var emptyListTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_playlists)

        emptyListTextView = findViewById(R.id.PublicPlaylistsEmptyTextView)
        plList = findViewById(R.id.publicPlaylistsLayout)
        Log.d("DEBUG","oaoaoao")

        //TODO \ HEALTHY attributes
        val parser: XmlPullParser = resources.getXml(R.xml.tw_attrs)
        val twAttributes: AttributeSet = Xml.asAttributeSet(parser)
        Log.d("DEBUG",twAttributes.toString())

        //TODO \ move this call to pm function or something like that
        db.collection("playlists").whereNotEqualTo(
            "status", PRIVATE_PLAYLIST_STATUS
        ).get().addOnSuccessListener { docs -> //returns only non-null documents
            Log.d("DEBUG",docs.size().toString())
            if (docs.size() != 0){
                emptyListTextView.visibility = View.INVISIBLE
            }
            for (doc in docs){
                var newTextView = TextView(this, twAttributes)
                newTextView.text = doc.data["title"].toString()

                //TODO !!!adding to favors now adds only the last
                pm.addToFavors(doc.data["id"].toString())
                plList.addView(newTextView)
                Log.d("PLLM","added 1")
            }
        }
    }
}