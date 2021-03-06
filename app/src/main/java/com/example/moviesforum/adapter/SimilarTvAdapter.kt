package com.example.moviesforum.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesforum.Model.TvModel.similartvresponse.SeasonsItem
import com.example.moviesforum.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.slider.view.*

class SimilarTvAdapter(val list: List<SeasonsItem>):
    RecyclerView.Adapter<SimilarTvAdapter.UserViewHolder>() {

    var onItemClick: ((user: SeasonsItem) -> Unit)? = null

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

        fun bind(user: SeasonsItem) {
            itemView.apply {
                sliderTv1.text = user.name
                sliderTv2.text = user.airDate
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.posterPath.toString()).into(sliderIv)
                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }
}