package com.example.moviesforum.adapter.childadapter.PeopleChildAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesforum.Model.PeopleModel.popularpeopleresponse.ResultsItem
import com.example.moviesforum.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.slider_people.view.*

class PeoplePopularAdapter(val list: List<ResultsItem>):
    RecyclerView.Adapter<PeoplePopularAdapter.UserViewHolder>() {

    var onItemClick: ((user: ResultsItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slider_people,
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
                sliderPeopleTv1.text = user.name
                sliderPeopleTv2.text = user.knownForDepartment
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + user.profilePath.toString()).into(sliderPeopleIv)
                setOnClickListener{
                    onItemClick?.invoke(user)
                }
            }
        }
    }
}