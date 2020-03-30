package com.example.moviesforum.TvFragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesforum.Model.TvModel.tvtopratedresponse.ResultsItem
import com.example.moviesforum.adapter.childadapter.TvChildAdapter.TopRatedTvAdapter
import com.example.moviesforum.client.Client
import com.example.moviesforum.DisplayTvActivity

import com.example.moviesforum.R
import kotlinx.android.synthetic.main.fragment_top_rated_tv.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 */
class TopRatedTvFragment : Fragment() {

    val list = arrayListOf<ResultsItem>()
    val topratedadapter = TopRatedTvAdapter(list)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val f= inflater.inflate(R.layout.fragment_top_rated_tv, container, false)

        f.topTvRv.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
            adapter = topratedadapter
        }
        topratedadapter.onItemClick = {
            Toast.makeText(context,"toprated tv", Toast.LENGTH_LONG).show()

            val intent = Intent(context,DisplayTvActivity::class.java)
            intent.putExtra("tvid",it.id.toString())
            startActivity(intent)
        }

        GlobalScope.launch {
            for (i in 1..10){
                val response = withContext(Dispatchers.IO){ Client.api.getAllTopRatedTv("${i}")}
                if (response.isSuccessful){
                    response.body()?.let { res ->
                        res.results?.let {
                            list.addAll(it)
                        }
                        activity?.runOnUiThread { topratedadapter.notifyDataSetChanged() }
                    }
                }
            }}
        return f
    }


}
