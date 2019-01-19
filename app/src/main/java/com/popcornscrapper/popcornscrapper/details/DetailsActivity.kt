package com.popcornscrapper.popcornscrapper.details

import android.os.Bundle
import com.popcornscrapper.popcornscrapper.BaseActivity
import com.popcornscrapper.popcornscrapper.BasePresenter
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieImdbTO
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
        presenter.getImdbDetails()
    }

    override fun setupMovieTitle(movieTitle: String) {
        movieTitleTextView.text = movieTitle
    }

    override fun setMovieDetails(imdbDetails: MovieImdbTO, metacriticRating: String?) {
        var imdbRating = "-"
        var metaRating = "-"
        directorTextView.text = imdbDetails.director
        movieDescriptionTextView.text = imdbDetails.plot
        if(imdbDetails.rating != null && imdbDetails.rating.isNotEmpty()){
            imdbRating = imdbDetails.rating + getString(R.string.per_ten)
        }
        if(metacriticRating != null && metacriticRating.isNotEmpty()){
            metaRating = metacriticRating + getString(R.string.per_houndred)
        }
        imdbRatingTextView.text = imdbRating
        metacriticRatingTextView.text = metaRating
        Picasso.get().load(imdbDetails.poster).into(posterImageView)
    }

    override fun showEmptyView(){

    }
}