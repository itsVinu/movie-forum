package com.example.moviesforum.Model.MovieModel.upcomingresponse

import com.google.gson.annotations.SerializedName

data class MovieUpcomingResponse(

	@field:SerializedName("dates")
	val dates: Dates? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)