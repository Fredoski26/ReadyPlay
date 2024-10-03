package com.example.readyplay.viewmodel

import com.example.readyplay.core.RetroInstance
import com.example.readyplay.model.MovieDetail
import com.example.readyplay.model.MovieList
import com.example.readyplay.model.MoviePopular
import retrofit2.Response

class Repository {
    suspend fun getMovieList(page:Int): Response<MovieList> {
        return RetroInstance.api.getMovies(page)
    }
    suspend fun getDetailsById(id:Int): Response<MovieDetail> {
        return RetroInstance.api.getDetailsById(id = id)
    }

    suspend fun getPopularMovies(page:Int): Response<MoviePopular> {
        return RetroInstance.api.getPopularMovies(page)
    }
}