package com.popcornscrapper.popcornscrapper.search

import android.content.Intent
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.ResUtil
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieListItem
import com.popcornscrapper.popcornscrapper.service.ServiceManager
import com.popcornscrapper.popcornscrapper.service.receivers.GetMoviesReceiver
import com.popcornscrapper.popcornscrapper.splash.SplashPresenter
import com.popcornscrapper.popcornscrapper.splash.SplashView


class SearchPresenterImpl(var view: SearchView) : SearchPresenter, GetMoviesReceiver {

    override fun initExtras(intent: Intent) {
    }

    override fun getMovies(title: String){
        view.startProgressDialog(ResUtil.getString(R.string.progress_loading_text))
        ServiceManager.getListOfMovies(this, title)
    }

    override fun onGetMoviesSuccess(movies: List<MovieListItem>) {
        view.stopProgressDialog()
        if(movies.isNotEmpty()) {
            view?.clearSearchEditText()
            view.navigateToMoviesList(movies)
        } else {
            view.showNoResultsToast()
        }
    }

    override fun onGetMovieError() {
        view.stopProgressDialog()
    }

}