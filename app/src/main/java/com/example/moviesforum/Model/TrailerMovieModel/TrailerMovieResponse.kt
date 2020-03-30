package com.example.moviesforum.Model.TrailerMovieModel

import com.google.gson.annotations.SerializedName

data class TrailerMovieResponse(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem>? = null
)