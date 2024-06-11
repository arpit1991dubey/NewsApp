package com.example.rvapp.di

import com.example.rvapp.data.network.MoviesApi
import com.example.rvapp.data.repository.ImovieRepository
import com.example.rvapp.data.repository.MoviesRepository
import com.example.rvapp.utils.Contants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideRepository(moviesApi: MoviesApi): ImovieRepository {
        return MoviesRepository(moviesApi)
    }

    @Provides
    fun provideRetrofit(): MoviesApi {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return Retrofit.Builder()
            .baseUrl(Contants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MoviesApi::class.java)
    }
}