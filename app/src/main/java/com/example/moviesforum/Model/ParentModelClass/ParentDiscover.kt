package com.example.moviesforum.Model.ParentModelClass

import com.example.moviesforum.DiscoverModel.discovermovieresponse.ResultsItem


data class ParentDiscover (
    val category: String,
    val movie : List<ResultsItem>,
    val tv : List<com.example.moviesforum.DiscoverModel.discovertvresponse.ResultsItem>
)