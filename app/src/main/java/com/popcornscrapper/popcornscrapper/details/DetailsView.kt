package com.popcornscrapper.popcornscrapper.details

import com.popcornscrapper.popcornscrapper.BaseView
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieImdbTO
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieMetacriticItemTO

interface DetailsView : BaseView {
    fun setupMovieTitle(movieTitle: String)
    fun fillMovieDetails(imdbDetails: MovieImdbTO, metacriticDetails: MovieMetacriticItemTO?)
}