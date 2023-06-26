package com.flirex.verbum.activities

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.AggregateSource
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class PlaylistManager {
    companion object{
        val auth: FirebaseAuth = Firebase.auth
        val db = Firebase.firestore
        val currentUser = auth.currentUser
    }

    fun addToFavors(playlistId: String){
        //TODO (it may be suspending with await of update)
        //TODO synchronizing playlistsList with db time-to-time

        var pll = playlistsList.toMutableList()
        if(playlistId !in playlistsList){
            //TODO \ WHY so CRUTCHY
            pll.add(playlistId)
            playlistsList = pll
        }
        Log.d("PLLM", playlistsList.toString())
        Log.d("PLLM", pll.toString())
        db.collection("users").document(currentUser!!.uid)
            .update("playlists", pll.joinToString(","))

//            }
//            else{
//                Log.d("DEBUG","bruh, you try to add added id")
//            }

    }

    suspend fun computeNewId(): String {
        return db.collection("playlists")
            .count()
            .get(AggregateSource.SERVER).await().count.toString()
    }

    suspend fun setPlaylist(
        title: String,
        about: String = "",
        words: MutableList<String> = mutableListOf(),
        author: String = currentUser!!.uid,
        status: String = PRIVATE_PLAYLIST_STATUS
    ): String {
        //TODO("a lot of successfulity checks")
        val id: String = computeNewId()

        db.collection("playlists")
            .document(id)
            .set(
                PlaylistData(
                    id,
                    author,
                    title,
                    about,
                    status,
                    words)
            ).addOnFailureListener { e -> Log.e("ErrorPlaylistSet", "crash man", e) }.await()
        return id
    }

    suspend fun getWordsFromPlaylist(id:String): MutableList<String>{
        val playlistData = getPlaylistDoc(id).data
        Log.d("words GET", "DocumentSnapshot data: ${playlistData}")
        return playlistData!!["words"]!! as MutableList<String>

//        val document = getPlaylistDoc(id)
//
//        if (document.data != null) {
//            Log.d("words GET", "DocumentSnapshot data: ${document.data}")
//            return document.get("words")!! as List<String>
//        } else {
//            Log.e("ErrorPlaylistGet", "No such document")
//            throw Exception()
//        }
    }

    suspend fun getPlaylistDoc(id:String): DocumentSnapshot {
        return db.collection("playlists")
            .document(id)
            .get().await()
    }

    suspend fun getPlaylist(id: String): PlaylistData {
        val playlistData = getPlaylistDoc(id).data
        return PlaylistData(
            playlistData!!["id"]!! as String,
            playlistData["author"]!! as String,
            playlistData["title"]!! as String,
            playlistData["about"] as String?,
            playlistData["status"] as String?,
            playlistData["words"] as MutableList<String>?,
        )
    }
}