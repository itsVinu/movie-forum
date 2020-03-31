package com.example.moviesforum

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moviesforum.Model.Wishes
import com.example.moviesforum.Model.Wishesmovies
import java.util.List

@Dao
interface WishesDao {

    @Insert
    suspend fun insert(user: Wishes)

    @Insert
    suspend fun insertAllUsers(user:List<Wishes>)

    @Query("select * from Wishes")
    fun getAllUsers():LiveData<List<Wishes>>

    @Query("Delete from Wishes where id= :uid")
    fun deleteTask(uid:Long)

    @Insert
    suspend fun insertMovies(user: Wishesmovies)

    @Insert
    suspend fun insertAllUsersMovies(user:List<Wishesmovies>)

    @Query("select * from Wishesmovies")
    fun getAllUsersMovies():LiveData<List<Wishesmovies>>

    @Query("Delete from Wishesmovies where id= :uid")
    fun deleteTaskMovies(uid:Long)

//    @Query("SELECT EXISTS (SELECT 1 FROM Wishesmovies WHERE id=:uid)")
//    fun isWish(uid: Long): Long

    @Query("select id from Wishesmovies")
    fun getAllUsersMoviesId():List<Long>

    @Query("select id from Wishes")
    fun getAllUsersTvId():List<Long>
}