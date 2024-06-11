package com.example.rvapp.data.repository

import android.util.Log
import com.example.rvapp.data.model.MovieResponseItem
import com.example.rvapp.data.network.MoviesApi
import com.example.rvapp.utils.NetworkState
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val moviesApi: MoviesApi) : ImovieRepository {
    override suspend fun getAllMovies(): NetworkState<List<MovieResponseItem>> {
        val response = moviesApi.getMovieList()
        Log.d("resBody", "getAllMovies:top $response")
        return if (response.isSuccessful) {
            val responseBody = response.body()
            Log.d("resBody", "getAllMovies: $responseBody")
            if (responseBody != null) {
                Log.d("resBody", "getAllMovies:suc $responseBody")

                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }

}