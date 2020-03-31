package com.example.moviesforum.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesforum.R
import com.example.moviesforum.Model.Wishesmovies
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.slider.view.*

class WishlistAdapterMovies(val user:List<Wishesmovies>):
    RecyclerView.Adapter<WishlistAdapterMovies.ItemVieHolder>(){

    var onItemClick:((user: Wishesmovies)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVieHolder {
        return ItemVieHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slider,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = user.size

    override fun onBindViewHolder(holder: ItemVieHolder, position: Int) {
        holder.bind(user[position])
    }

    override fun getItemId(position: Int): Long {
        return user[position].id!!
    }

    inner class ItemVieHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(user: Wishesmovies){
            itemView.apply {
                sliderTv1.text = user.name+"(${user.original_name})"
                sliderTv2.text = user.overview

                Picasso.get().load("https://image.tmdb.org/t/p/w500"+user.posterPath.toString()).into(sliderIv)
                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }

}