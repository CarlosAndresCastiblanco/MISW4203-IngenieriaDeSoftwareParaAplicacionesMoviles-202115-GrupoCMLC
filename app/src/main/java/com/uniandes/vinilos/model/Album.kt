package com.uniandes.vinilos.model

data class Album(
    var id: String,
    var name: String,
    var cover: String,
    var releaseDate: String,
    var genre: String,
    var recordLabel: String,
    var description: String,
    var performers: List<Performer>,
    var comments: List<Comment>
)