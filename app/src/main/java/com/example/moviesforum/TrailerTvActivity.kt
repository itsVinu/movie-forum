package com.example.moviesforum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesforum.Model.TvModel.similartvresponse.SeasonsItem
import com.example.moviesforum.adapter.SimilarTvAdapter
import com.example.moviesforum.client.Client
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_trailer_tv.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrailerTvActivity : AppCompatActivity() {

    val list = arrayListOf<SeasonsItem>()
    val similartvadapter = SimilarTvAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer_tv)

        val tv_id = intent.getStringExtra("tvid")


        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){ Client.api.getAllTvTrailer(tv_id)}
            if (response.isSuccessful){
                if (response.body()?.results.isNullOrEmpty()){
                   GlobalScope.launch(Dispatchers.Main)
                    { Toast.makeText(this@TrailerTvActivity,"Trailer not available", Toast.LENGTH_LONG).show() }
                }else{
                    response.body()?.let {res->
                        res.results?.let {  }
                        runOnUiThread{
                            lifecycle.addObserver(youtube_button)
                            youtube_button.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
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
            layoutManager = LinearLayoutManager(this@TrailerTvActivity,RecyclerView.VERTICAL,false)
            adapter = similartvadapter
        }

        similartvadapter.onItemClick = {
            Toast.makeText(this@TrailerTvActivity,"similar tv",Toast.LENGTH_LONG).show()

            val intent = Intent(this,SimilarTrailerTvActivity::class.java)
            intent.putExtra("tvid",it.id.toString())
            startActivity(intent)

        }

        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){Client.api.getAllSimilarTv(tv_id)}
            if (response.isSuccessful){
                response.body()?.let { res->
                    res.seasons?.let {
                        list.addAll(it)
                    }
                    runOnUiThread{similartvadapter.notifyDataSetChanged()}
                }
            }
        }


    }
}
