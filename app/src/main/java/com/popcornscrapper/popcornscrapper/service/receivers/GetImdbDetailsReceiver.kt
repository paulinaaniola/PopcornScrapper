package com.popcornscrapper.popcornscrapper.service.receivers

import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieImdbTO


interface GetImdbDetailsReceiver {
    fun onGetImdbDetailsSuccess(movie: MovieImdbTO)
    fun onGetImdbDetailsError()
}