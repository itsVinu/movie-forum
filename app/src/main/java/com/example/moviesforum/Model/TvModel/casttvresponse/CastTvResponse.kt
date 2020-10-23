package com.example.moviesforum.Model.TvModel.casttvresponse

import com.google.gson.annotations.SerializedName

data class CastTvResponse(

	@field:SerializedName("cast")
	val cast: List<CastItem>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("crew")
	val crew: List<Any>? = null
)