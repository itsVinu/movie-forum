package com.example.moviesforum

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class movieWishlist (

    val name:String,
    val year:String,
    val image:String,
    @PrimaryKey(autoGenerate = false)
    val id:String

)