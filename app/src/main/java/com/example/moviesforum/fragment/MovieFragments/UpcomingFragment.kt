package com.example.moviesforum.fragment.MovieFragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesforum.Model.MovieModel.upcomingresponse.ResultsItem
import com.example.moviesforum.adapter.childadapter.MovieChildAdapter.UpcomingMovieAdapter
import com.example.moviesforum.client.Client
import com.example.moviesforum.DisplayActivity
import com.example.moviesforum.R
import kotlinx.android.synthetic.main.fragment_upcoming.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 */
class UpcomingFragment : Fragment() {

    val list = arrayListOf<ResultsItem>()
    val upcomingadapter = UpcomingMovieAdapter(list)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val f= inflater.inflate(R.layout.fragment_upcoming, container, false)

        f.upcomingRv.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = upcomingadapter
            }
            upcomingadapter.onItemClick = {
                Toast.makeText(context, "upcoming", Toast.LENGTH_LONG).show()

                val intent = Intent(context,DisplayActivity::class.java)
                intent.putExtra("movieid",it.id.toString())
                startActivity(intent)
            }

            GlobalScope.launch {
                for (i in 1..10) {
                val response = withContext(Dispatchers.IO) { Client.api.getAllUpcomingMovies("${i}") }
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        res.results?.let {
                            list.addAll(it)
                        }
                        activity?.runOnUiThread { upcomingadapter.notifyDataSetChanged() }
                    }
                }
            }
        }
        return f
    }
}
