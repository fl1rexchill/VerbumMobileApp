package com.flirex.verbum.activities

data class PlaylistData(
    val id:String,
    val author:String,
    val title:String,
    val about:String?,
    val status:String?,
    val words:MutableList<String>?
)