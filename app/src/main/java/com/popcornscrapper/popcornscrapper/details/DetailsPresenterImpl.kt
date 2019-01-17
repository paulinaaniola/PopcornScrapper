package com.popcornscrapper.popcornscrapper.details

import android.content.Intent
import com.popcornscrapper.popcornscrapper.model.utils.IntentKeys

class DetailsPresenterImpl(var view: DetailsView) : DetailsPresenter {

    var movieId: Int? = null

    override fun initExtras(intent: Intent) {
        movieId = intent.getIntExtra(IntentKeys.MOVIE_ID,0)
    }
}