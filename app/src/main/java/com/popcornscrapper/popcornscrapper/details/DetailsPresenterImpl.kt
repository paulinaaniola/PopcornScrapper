package com.popcornscrapper.popcornscrapper.details

import android.content.Intent
import com.popcornscrapper.popcornscrapper.model.utils.IntentKeys

class DetailsPresenterImpl(var view: DetailsView) : DetailsPresenter {

    private val presentationModel: DetailsModel = DetailsModel()

    override fun initExtras(intent: Intent) {
        val movieId = intent.getSerializableExtra(IntentKeys.MOVIE_ID) as? String
        val movieTitle = intent.getSerializableExtra(IntentKeys.MOVIE_TITLE) as? String
        movieId?.let { presentationModel.movieId = it }
        movieTitle?.let { presentationModel.movieTitle = it }
        view.setupMovieTitle(presentationModel.movieTitle)
    }
}