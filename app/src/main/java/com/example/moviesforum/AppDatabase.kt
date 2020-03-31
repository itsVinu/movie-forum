package com.example.moviesforum

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Wishes::class,Wishesmovies::class],version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun wishesdao():WishesDao
}