package com.example.moviesforum

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesforum.Model.Wishes
import com.example.moviesforum.Model.Wishesmovies

@Database(entities = [Wishes::class, Wishesmovies::class],version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun wishesdao():WishesDao
}