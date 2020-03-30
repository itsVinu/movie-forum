package com.example.moviesforum.Model.ParentModelClass

import com.example.moviesforum.Model.TvModel.tvairingtodayresponse.ResultsItem

data class ParentTvSeries(
    val category: String,
    val airingtoday: ArrayList<ResultsItem>,
    val ontheair: ArrayList<com.example.moviesforum.Model.TvModel.tvontheairresponse.ResultsItem>,
    val toprated: ArrayList<com.example.moviesforum.Model.TvModel.tvtopratedresponse.ResultsItem>,
    val popular: ArrayList<com.example.moviesforum.Model.TvModel.tvpopularresponse.ResultsItem>
)