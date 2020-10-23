package com.example.moviesforum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesforum.Model.MovieModel.similarmovieresponse.ResultsItem
import com.example.moviesforum.adapter.SimilarMovieAdapter
import com.example.moviesforum.client.Client
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_trailer.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrailerMovieActivity : AppCompatActivity() {

    val list = arrayListOf<ResultsItem>()
    val similaradapter = SimilarMovieAdapter(list)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)

        val movie_id = intent.getStringExtra("movieid")!!


        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){ Client.api.getAllMovieTrailer(movie_id)}
            if (response.isSuccessful){
                if (response.body()?.results.isNullOrEmpty()){
                   GlobalScope.launch (Dispatchers.Main)
                   { Toast.makeText(this@TrailerMovieActivity,"Trailer not available",Toast.LENGTH_LONG).show() }
                }else{
                    response.body()?.let {res->
                        res.results?.let {  }
                        runOnUiThread{
                            lifecycle.addObserver(youtube)
                            youtube.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    val videoId:String = res.results?.get(0)?.key.toString()
                                    youTubePlayer.loadVideo(videoId, 0F)
                                    super.onReady(youTubePlayer)
                                }
                            })
                        }
                    }
                }
            }
        }

        similarRv.apply {
            layoutManager = LinearLayoutManager(this@TrailerMovieActivity,RecyclerView.VERTICAL,false)
            adapter = similaradapter
        }

        similaradapter.onItemClick = {
            Toast.makeText(this@TrailerMovieActivity,"similar",Toast.LENGTH_LONG).show()

            val intent = Intent(this,SimilarMovieTrailerActivity::class.java)
            intent.putExtra("movieid",it.id.toString())
            startActivity(intent)

        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){Client.api.getAllSimilarMovie(movie_id)}
            if (response.isSuccessful){
                response.body()?.let { res->
                    res.results?.let {
                        list.addAll(it)
                    }
                    runOnUiThread{similaradapter.notifyDataSetChanged()}
                }
            }
        }
    }
}
