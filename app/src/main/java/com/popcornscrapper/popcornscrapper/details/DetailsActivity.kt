package com.popcornscrapper.popcornscrapper.details

import android.os.Bundle
import com.popcornscrapper.popcornscrapper.BaseActivity
import com.popcornscrapper.popcornscrapper.BasePresenter
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.movies.MoviesPresenter
import com.popcornscrapper.popcornscrapper.movies.MoviesPresenterImpl
import com.popcornscrapper.popcornscrapper.movies.MoviesView

class DetailsActivity : BaseActivity(), DetailsView {

    private lateinit var presenter: DetailsPresenter

    override fun providePresenter(): BasePresenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        presenter = DetailsPresenterImpl(this)
    }
}