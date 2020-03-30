package com.example.moviesforum.adapter.childadapter.DiscoverChildAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesforum.DiscoverModel.discovermovieresponse.ResultsItem
import com.example.moviesforum.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.slider.view.*

class MovieDiscoverAdapter(val list: List<ResultsItem>):
    RecyclerView.Adapter<MovieDiscoverAdapter.UserViewHolder>() {

    var onItemClick: ((user: ResultsItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slider,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(user: ResultsItem) {
            itemView.apply {
                sliderTv1.text = user.originalTitle
                sliderTv2.text = user.releaseDate
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.posterPath.toString()).into(sliderIv)
                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }
}