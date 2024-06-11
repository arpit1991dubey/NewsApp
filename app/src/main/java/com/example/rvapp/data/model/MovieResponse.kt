package com.example.rvapp.data.model

class MovieResponse : ArrayList<MovieResponseItem>()

data class MovieResponseItem(
    val Poster: String,
    val Runtime: String,
    val Title: String,
    val Year: String
)