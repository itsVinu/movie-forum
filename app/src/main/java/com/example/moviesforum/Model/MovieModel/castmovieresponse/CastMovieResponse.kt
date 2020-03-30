package com.example.moviesforum.Model.MovieModel.castmovieresponse

import com.google.gson.annotations.SerializedName

data class CastMovieResponse(

	@field:SerializedName("cast")
	val cast: List<CastItem?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("crew")
	val crew: List<CrewItem>? = null
)