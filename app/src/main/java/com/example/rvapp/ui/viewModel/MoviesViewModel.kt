package com.example.rvapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rvapp.data.model.MovieResponseItem
import com.example.rvapp.data.repository.MoviesRepository
import com.example.rvapp.utils.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    val movieList = MutableLiveData<List<MovieResponseItem>>()


    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    val loading = MutableLiveData<Boolean>()
    fun getAllMovies() {
        Log.d("Thread Outside", Thread.currentThread().name)
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            Log.d("Thread Inside", Thread.currentThread().name)
            when (val response = moviesRepository.getAllMovies()) {
                is NetworkState.Success -> {
                    movieList.postValue(response.data)
                    Log.d("Thread Inside", "${response.data}")
                }
                is NetworkState.Error -> {
                    Log.d("Thread Inside", "${response}")

                    if (response.response.code() == 401) {
                        //movieList.postValue(NetworkState.Error())
                    } else {
                        //movieList.postValue(NetworkState.Error)
                    }
                }
            }
        }
    }

    private fun onError(message: String) {
        _errorMessage.value = message
        loading.value = false
    }

}