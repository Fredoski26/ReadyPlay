package com.example.readyplay.network


import com.example.readyplay.model.MovieDetail
import com.example.readyplay.model.MovieList
import com.example.readyplay.model.MoviePopular
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movies?")
    suspend fun getMovies(
        @Query("page")page: Int
    ): Response<MovieList>

    @GET("movies/{movie_id}")
    suspend fun getDetailsById(
        @Path("movie_id")id: Int
    ): Response<MovieDetail>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page")page: Int
    ): Response<MoviePopular>


}
