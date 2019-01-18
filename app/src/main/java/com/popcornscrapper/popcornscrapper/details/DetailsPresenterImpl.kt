package com.popcornscrapper.popcornscrapper.details

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Intent
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.ResUtil
import com.popcornscrapper.popcornscrapper.model.utils.IntentKeys
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieImdbTO
import com.popcornscrapper.popcornscrapper.service.ServiceManager
import com.popcornscrapper.popcornscrapper.service.receivers.GetImdbDetailsReceiver

class DetailsPresenterImpl(var view: DetailsView) : DetailsPresenter, GetImdbDetailsReceiver {

    private val presentationModel: DetailsModel = DetailsModel()

    override fun initExtras(intent: Intent) {
        val movieId = intent.getSerializableExtra(IntentKeys.MOVIE_ID) as? String
        val movieTitle = intent.getSerializableExtra(IntentKeys.MOVIE_TITLE) as? String
        movieId?.let { presentationModel.movieId = it }
        movieTitle?.let { presentationModel.movieTitle = it }
        view.setupMovieTitle(presentationModel.movieTitle)
    }

    override fun getImdbDetails(){
        view.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        ServiceManager.getImdbDetails(this, presentationModel.movieId)
    }

    override fun onGetImdbDetailsSuccess(movie: List<MovieImdbTO>) {
        view.setMovieDetails(movie[0])
        view.stopProgressDialog()
    }

    override fun onGetImdbDetailsError() {
        view.stopProgressDialog()
    }
}