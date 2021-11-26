package com.uniandes.vinilos.model

data class CommentResponse(
val description: String,
val rating: Long,
val collector: Collector,
val album: Album,
val id: Long
)
