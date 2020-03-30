package com.example.moviesforum.MovieFragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesforum.Model.MovieModel.topratedresponse.ResultsItem
import com.example.moviesforum.adapter.childadapter.MovieChildAdapter.TopRatedMovieAdapter
import com.example.moviesforum.client.Client
import com.example.moviesforum.DisplayActivity
import com.example.moviesforum.R
import kotlinx.android.synthetic.main.fragment_top_rated.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 */
class TopRatedFragment : Fragment() {

    val list = arrayListOf<ResultsItem>()
    val topratedadapter = TopRatedMovieAdapter(list)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val f = inflater.inflate(R.layout.fragment_top_rated, container, false)

        f.topRv.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
            adapter = topratedadapter
        }
        topratedadapter.onItemClick = {
            Toast.makeText(context,"toprated", Toast.LENGTH_LONG).show()

            val intent = Intent(context,DisplayActivity::class.java)
            intent.putExtra("movieid",it.id.toString())
            startActivity(intent)
        }

        GlobalScope.launch {
            for (i in 1..10) {
                val response = withContext(Dispatchers.IO) { Client.api.getAllTopRatedMovies("${i}") }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.results?.let {
                            list.addAll(it)
                        }
                        activity?.runOnUiThread { topratedadapter.notifyDataSetChanged() }
                    }
                }
            }
        }
        return f
    }


}
