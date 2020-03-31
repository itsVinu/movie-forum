package com.example.moviesforum.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wishesmovies(
    val name:String,
    val original_name:String,
    val posterPath:String,
    val overview:String,
    @PrimaryKey(autoGenerate = false)
    val id: Long? = 0L
)