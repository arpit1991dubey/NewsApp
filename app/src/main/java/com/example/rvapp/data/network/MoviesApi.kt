package com.example.rvapp.data.network

import com.example.rvapp.data.model.MovieResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApi {
    @GET("fake-movies-api/movies")
    suspend fun getMovieList(): Response<List<MovieResponseItem>>
}