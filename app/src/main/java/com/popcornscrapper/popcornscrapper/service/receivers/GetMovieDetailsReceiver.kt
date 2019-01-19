package com.popcornscrapper.popcornscrapper.service.receivers

import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieImdbTO
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieMetacriticItemTO


interface GetMovieDetailsReceiver {
    fun onGetDetailsSuccess(imdbDetails: MovieImdbTO?, metacriticRating: MovieMetacriticItemTO?)
    fun onGetDetailsError()
}