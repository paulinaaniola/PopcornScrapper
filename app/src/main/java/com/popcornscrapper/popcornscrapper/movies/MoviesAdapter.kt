package com.popcornscrapper.popcornscrapper.movies

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieListItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MoviesAdapter(val movies: List<MovieListItem>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        val holder = ViewHolder(v)
        return holder
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieTitle.text = movies[position].title
        holder.productionYearTextView.text = movies[position].year
        holder.movieItemLayout.setOnClickListener { (context as MoviesView).navigateToDetailsActivity(movies[position])}
        Picasso.get().load(movies[position].poster).into(holder.movieImageView)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productionYearTextView: TextView = view.productionYearTextView
        val movieImageView: ImageView = view.movieImageView
        val movieTitle: TextView = view.movieTitleTextView
        val movieItemLayout: LinearLayout = view.movieItemLayout
    }
}
