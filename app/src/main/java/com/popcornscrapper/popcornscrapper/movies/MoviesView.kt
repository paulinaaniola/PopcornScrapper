package com.popcornscrapper.popcornscrapper.movies

import com.popcornscrapper.popcornscrapper.BaseView
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieListItem

interface MoviesView : BaseView {
    fun setupMoviesAdapter(movies: List<MovieListItem>?)
}