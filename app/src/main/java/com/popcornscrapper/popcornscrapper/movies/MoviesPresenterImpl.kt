package com.popcornscrapper.popcornscrapper.movies

import android.content.Intent
import com.popcornscrapper.popcornscrapper.model.utils.IntentKeys
import com.popcornscrapper.popcornscrapper.model.utils.businessobjects.MoviesDTO


class MoviesPresenterImpl(var view: MoviesView) : MoviesPresenter {

    override fun initExtras(intent: Intent) {
        val movies = intent.getSerializableExtra(IntentKeys.MOVIES) as? MoviesDTO
        view.setupMoviesAdapter(movies?.movies)
    }
}