package com.popcornscrapper.popcornscrapper.details

import android.content.Intent
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.ResUtil
import com.popcornscrapper.popcornscrapper.model.utils.IntentKeys
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieImdbTO
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieMetacriticItemTO
import com.popcornscrapper.popcornscrapper.service.ServiceManager
import com.popcornscrapper.popcornscrapper.service.receivers.GetMovieDetailsReceiver

class DetailsPresenterImpl(var view: DetailsView) : DetailsPresenter, GetMovieDetailsReceiver {

    private val presentationModel: DetailsModel = DetailsModel()

    override fun initExtras(intent: Intent) {
        val movieId = intent.getSerializableExtra(IntentKeys.MOVIE_ID) as? String
        val movieTitle = intent.getSerializableExtra(IntentKeys.MOVIE_TITLE) as? String
        movieId?.let { presentationModel.movieId = it }
        movieTitle?.let { presentationModel.movieTitle = it }
        view.setupMovieTitle(presentationModel.movieTitle)
    }

    override fun getMovieDetails() {
        view.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        ServiceManager.getMovieDetails(this, presentationModel.movieId, presentationModel.movieTitle.toLowerCase())
    }

    override fun onGetDetailsSuccess(imdbDetails: MovieImdbTO?, metacriticRating: MovieMetacriticItemTO?) {
        imdbDetails?.let {
            view.fillMovieDetails(imdbDetails, metacriticRating)
        }
        view.stopProgressDialog()
    }

    override fun onGetDetailsError() {
        view.stopProgressDialog()
    }
}