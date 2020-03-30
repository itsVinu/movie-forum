package com.example.moviesforum

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.util.List

@Dao
interface wishlistDao{

    @Insert
    fun insertUser(user: movieWishlist):Long

    @Insert
    fun insertAllUser(users: List<movieWishlist>)

    @Query("Select * From movieWishlist")
    fun getAllUsers(): LiveData<List<movieWishlist>>          //LiveData is used to show all the elements after they are entered
    // not just getting them stored but getting them displayed also

    @Query("Select * From movieWishlist where id > :elder")
    fun getAllUser(elder:Int): List<movieWishlist>

    @Delete
    fun deleteUser(user: movieWishlist)

}