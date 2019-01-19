package com.popcornscrapper.popcornscrapper.details

import com.popcornscrapper.popcornscrapper.BaseView
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieImdbTO

interface DetailsView : BaseView {
    fun setupMovieTitle(movieTitle: String)
    fun setMovieDetails(imdbDetails: MovieImdbTO, metacriticRating: String?)
}