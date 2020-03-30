package com.example.moviesforum.Model.ParentModelClass

import com.example.moviesforum.Model.MovieModel.popularresponse.ResultsItem

data class ParentMovies (
    val category: String,
    val popular : List<ResultsItem>,
    val upcoming: List<com.example.moviesforum.Model.MovieModel.upcomingresponse.ResultsItem>,
    val toprated : List<com.example.moviesforum.Model.MovieModel.topratedresponse.ResultsItem>,
    val nowplaying : List<com.example.moviesforum.Model.MovieModel.nowplayingresponse.ResultsItem>
)