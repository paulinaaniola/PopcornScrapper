package com.popcornscrapper.popcornscrapper.details

import android.os.Bundle
import com.popcornscrapper.popcornscrapper.BaseActivity
import com.popcornscrapper.popcornscrapper.BasePresenter
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieImdbTO
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieMetacriticItemTO
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : BaseActivity(), DetailsView {

    private lateinit var presenter: DetailsPresenterImpl

    override fun providePresenter(): BasePresenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        presenter = DetailsPresenterImpl(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getMovieDetails()
    }

    override fun setupMovieTitle(movieTitle: String) {
        movieTitleTextView.text = movieTitle
    }

    override fun fillMovieDetails(imdbDetails: MovieImdbTO, metacriticDetails: MovieMetacriticItemTO?) {
        directorTextView.text = imdbDetails.director
        imdbDetails.plot?.let {
            movieDescriptionTextView.text = it
        } ?: getString(R.string.no_plot)
        fillImdbRating(imdbDetails)
        fillMetacriticDetails(metacriticDetails, imdbDetails.year)
        Picasso.get().load(imdbDetails.poster).into(posterImageView)
    }

    private fun fillImdbRating(
        imdbDetails: MovieImdbTO
    ) {
        var imdbRating = "-"
        imdbDetails.rating?.let {
            if (it.isNotEmpty()) {
                imdbRating = it + getString(R.string.per_ten)
            }
            imdbRatingTextView.text = imdbRating
        }
    }

    private fun fillMetacriticDetails(
        metacriticDetails: MovieMetacriticItemTO?,
        imdbYear: String?
    ) {
        var metaRating = "-"
        metacriticDetails?.let {
            if (it.rating != null && it.rating.isNotEmpty() && it.year == imdbYear) {
                metaRating = it.rating + getString(R.string.per_houndred)
            }
        }
        metacriticRatingTextView.text = metaRating
    }
}