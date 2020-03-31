package com.example.moviesforum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.moviesforum.Model.MovieModel.castmovieresponse.CrewItem
import com.example.moviesforum.adapter.castadapter.CastMovieAdapter
import com.example.moviesforum.client.Client
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_display.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DisplayActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    val list = arrayListOf<CrewItem>()
    val castmovieadapter = CastMovieAdapter(list)

    val db by lazy {
        Room.databaseBuilder(this,
            AppDatabase::class.java,
            "app.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    var trigger = MutableLiveData<Boolean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        setSupportActionBar(toolbar1)

        val movie_id = intent.getStringExtra("movieid")


        trailerBtn.setOnClickListener{
            val intent = Intent(this,TrailerMovieActivity::class.java)
            intent.putExtra("movieid",movie_id)
            startActivity(intent)
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getAllDetailMovies(movie_id)}
            if (response.isSuccessful){
                response.body()?.let {
                    runOnUiThread{
                        detailTv1.text = it.originalTitle
                        detailTv2.text = it.releaseDate
                        detailTv3.text = it.overview
                        Picasso.get().load("https://image.tmdb.org/t/p/w500" + it.posterPath.toString()).into(detailIv)
                        Picasso.get().load("https://image.tmdb.org/t/p/w500" + it.posterPath.toString()).into(detailIv2)
                    }
                }
            }
        }

        mainDispRv.apply {
            layoutManager = LinearLayoutManager(this@DisplayActivity, RecyclerView.HORIZONTAL, false)
            adapter = castmovieadapter
        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) { Client.api.getAllCastMovies(movie_id) }
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    res.crew?.let {
                        list.addAll(it)
                    }
                    runOnUiThread { castmovieadapter.notifyDataSetChanged() }
                }
            }
        }

        var lists = arrayListOf<Long>()

        wishlistBtn.setOnClickListener {
            val i = Intent(this,WishlistMovieActivity::class.java)

            trigger.value = false

            GlobalScope.launch {
                val response = withContext(Dispatchers.IO){Client.api.getAllDetailMovies(movie_id)}

                if (response.isSuccessful){
                    response.body()?.let {
                        GlobalScope.launch(Dispatchers.Main) {
                            val a = withContext(Dispatchers.IO){
                                lists = db.wishesdao().getAllUsersMoviesId() as ArrayList<Long>
                                if (lists.contains(movie_id.toLong())){
                                    GlobalScope.launch(Dispatchers.Main) {
                                        Toast.makeText(this@DisplayActivity,"Present in the wishlist",Toast.LENGTH_SHORT).show()
                                    }
                                }
                                else{
                                    db.wishesdao().insertMovies(
                                        Wishesmovies(it.title.toString(),it.originalTitle.toString(),
                                            it.posterPath.toString(),it.overview.toString(), it.id?.toLong())
                                    )
                                    GlobalScope.launch(Dispatchers.Main) {
                                        Toast.makeText(this@DisplayActivity,"Added To Wishlist", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    }
                }
            }
            i.putExtra("movieid",movie_id)
        }


        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar1,
            R.string.open,
            R.string.close
        )

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.movie -> {
                startActivity(Intent(this,MainActivity::class.java))
                // Toast.makeText(this,"MOVIE", Toast.LENGTH_SHORT).show()
            }
            R.id.tvSeries ->{
                startActivity(Intent(this,Main2Activity::class.java))
            }
            R.id.discover ->{
                startActivity(Intent(this,Main3Activity::class.java))
            }
            R.id.wishlistMovie ->{
                startActivity(Intent(this,WishlistMovieActivity::class.java))
            }
            R.id.wishlistTvSeries ->{
                startActivity(Intent(this,WishlistTvActivity::class.java))
            }
        }
        drawer.closeDrawer(GravityCompat.START)   //used to close the navigation drawer when the items inside the drawer are clicked
        return true
    }
}