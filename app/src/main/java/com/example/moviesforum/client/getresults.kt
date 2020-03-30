package com.example.moviesforum.client

import com.example.moviesforum.DiscoverModel.discovermovieresponse.DiscoverMovieResponse
import com.example.moviesforum.DiscoverModel.discovertvresponse.DiscoverTvResponse
import com.example.moviesforum.Model.MovieModel.castmovieresponse.CastMovieResponse
import com.example.moviesforum.Model.MovieModel.detailresponse.DetailMovieResponse
import com.example.moviesforum.Model.MovieModel.nowplayingresponse.MovieNowPlayingResponse
import com.example.moviesforum.Model.MovieModel.popularresponse.MoviePopularResponse
import com.example.moviesforum.Model.MovieModel.similarmovieresponse.SimilarMovieResponse
import com.example.moviesforum.Model.MovieModel.topratedresponse.MovieTopRatedResponse
import com.example.moviesforum.Model.MovieModel.upcomingresponse.MovieUpcomingResponse
import com.example.moviesforum.Model.PeopleModel.peopledetailresponse.PeopleDetailResponse
import com.example.moviesforum.Model.PeopleModel.popularpeopleresponse.PeoplePopularResponse
import com.example.moviesforum.Model.SearchModel.SearchResponse
import com.example.moviesforum.Model.TrailerMovieModel.TrailerMovieResponse
import com.example.moviesforum.Model.TrailerTvModel.TrailerTvResponse
import com.example.moviesforum.Model.TvModel.casttvresponse.CastTvResponse
import com.example.moviesforum.Model.TvModel.similartvresponse.SimilarTvResponse
import com.example.moviesforum.Model.TvModel.tvairingtodayresponse.TvAiringTodayResponse
import com.example.moviesforum.Model.TvModel.tvdetailresponse.DetailTvResponse
import com.example.moviesforum.Model.TvModel.tvontheairresponse.TvOnTheAirResponse
import com.example.moviesforum.Model.TvModel.tvpopularresponse.TvPopularResponse
import com.example.moviesforum.Model.TvModel.tvtopratedresponse.TvTopRatedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface getresults {

    @GET("movie/upcoming?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllUpcomingMovies(@Query("page")page:String):Response<MovieUpcomingResponse>

    @GET("movie/popular?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllPopularMovies(@Query("page")page:String):Response<MoviePopularResponse>

    @GET("movie/top_rated?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllTopRatedMovies(@Query("page")page:String):Response<MovieTopRatedResponse>

    @GET("movie/now_playing?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllNowPlayingMovies(@Query("page")page:String):Response<MovieNowPlayingResponse>

    @GET("tv/airing_today?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllAiringTodayTv(@Query("page")page:String) : Response<TvAiringTodayResponse>

    @GET("tv/on_the_air?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllOnTheAirTv(@Query("page")page:String) : Response<TvOnTheAirResponse>

    @GET("tv/top_rated?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllTopRatedTv(@Query("page")page:String) : Response<TvTopRatedResponse>

    @GET("tv/popular?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllPopularTv(@Query("page")page:String) : Response<TvPopularResponse>

    @GET("discover/tv?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllTvDiscover(@Query("page")page:String) : Response<DiscoverTvResponse>

    @GET("discover/movie?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllMovieDiscover(@Query("page")page:String) : Response<DiscoverMovieResponse>

    @GET("person/popular?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllPeoplePopular(@Query("page")page:String) : Response<PeoplePopularResponse>

    @GET("movie/{movie_id}?api_key=3d812b53e8327e3496dbe05fb820cf0d&language=en-US")
    suspend fun getAllDetailMovies(@Path("movie_id")movie_id:String):Response<DetailMovieResponse>

    @GET("tv/{tv_id}?api_key=3d812b53e8327e3496dbe05fb820cf0d&language=en-US")
    suspend fun getAllDetailTv(@Path("tv_id")tv_id:String):Response<DetailTvResponse>

    @GET("movie/{movie_id}/credits?api_key=3d812b53e8327e3496dbe05fb820cf0d&language=en-US")
    suspend fun getAllCastMovies(@Path("movie_id")movie_id:String):Response<CastMovieResponse>

    @GET("tv/{tv_id}/credits?api_key=3d812b53e8327e3496dbe05fb820cf0d&language=en-US")
    suspend fun getAllCastTv(@Path("tv_id")tv_id:String):Response<CastTvResponse>

    @GET("person/{person_id}?api_key=3d812b53e8327e3496dbe05fb820cf0d&language=en-US")
    suspend fun getAllPeopleDetails(@Path("person_id")person_id:String):Response<PeopleDetailResponse>

    @GET("search/movie?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getSearch(@Query("query")query:String) : Response<SearchResponse>

    @GET("movie/{movie_id}/videos?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllMovieTrailer(@Path("movie_id")movie_id: String) : Response<TrailerMovieResponse>

    @GET("tv/{tv_id}/videos?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllTvTrailer(@Path("tv_id")tv_id: String) : Response<TrailerTvResponse>

    @GET("movie/{movie_id}/similar?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllMovieSimilar(@Path("movie_id")movie_id: String) : Response<SimilarMovieResponse>

    @GET("tv/{tv_id}/similar?api_key=3d812b53e8327e3496dbe05fb820cf0d")
    suspend fun getAllSimilarTv(@Path("tv_id")tv_id: String) : Response<SimilarTvResponse>

}
