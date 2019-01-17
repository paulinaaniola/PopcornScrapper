package com.popcornscrapper.popcornscrapper.service.receivers

import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieListItem

interface GetMoviesReceiver{
    fun onGetMoviesSuccess(movies: List<MovieListItem>)
    fun onGetMovieError()
}