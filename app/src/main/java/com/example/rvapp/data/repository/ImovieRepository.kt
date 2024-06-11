package com.example.rvapp.data.repository

import com.example.rvapp.data.model.MovieResponseItem
import com.example.rvapp.utils.NetworkState

interface ImovieRepository {
    suspend fun getAllMovies(): NetworkState<List<MovieResponseItem>>
}