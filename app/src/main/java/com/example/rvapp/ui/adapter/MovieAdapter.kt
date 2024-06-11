package com.example.rvapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rvapp.data.model.MovieResponseItem
import com.example.rvapp.databinding.AdapterMovieBinding

class MovieAdapter : RecyclerView.Adapter<MainViewHolder>() {
    var movieList = mutableListOf<MovieResponseItem>()

    fun setMovies(movies: List<MovieResponseItem>) {
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val movie = movieList[position]
        holder.binding.name.text = movie.Title
        Glide.with(holder.itemView.context).load(movie.Poster).into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

}