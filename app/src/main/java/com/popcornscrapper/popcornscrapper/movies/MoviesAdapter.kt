package com.popcornscrapper.popcornscrapper.movies

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.Movie
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MoviesAdapter(val movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        val holder = ViewHolder(v)
        return holder
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieTitle.text = movies[position].title
        holder.productionNameTextView.text = movies[position].productionYear
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productionNameTextView: TextView = view.productionYearTextView
        val movieImageView: ImageView = view.movieImageView
        val movieTitle: TextView = view.movieTitleTextView
    }
}
