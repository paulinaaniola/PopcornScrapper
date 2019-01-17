package com.popcornscrapper.popcornscrapper.service.receivers

import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieMetacriticItemTO

interface GetMetacriticRatingReceiver {
    fun onGetMetacriticRatingSuccess(rating: MovieMetacriticItemTO)
    fun onGetMetacriticRatingError()
}