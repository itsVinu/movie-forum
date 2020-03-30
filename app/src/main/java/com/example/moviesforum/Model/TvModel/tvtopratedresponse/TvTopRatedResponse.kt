package com.example.moviesforum.Model.TvModel.tvtopratedresponse

import com.google.gson.annotations.SerializedName

data class TvTopRatedResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)