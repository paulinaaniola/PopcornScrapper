package com.popcornscrapper.popcornscrapper.movies

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.popcornscrapper.popcornscrapper.ApplicationContext
import com.popcornscrapper.popcornscrapper.BaseActivity
import com.popcornscrapper.popcornscrapper.BasePresenter
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.details.DetailsActivity
import com.popcornscrapper.popcornscrapper.model.utils.IntentKeys
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.Movie
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieListItem
import com.popcornscrapper.popcornscrapper.search.SearchActivity
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : BaseActivity(), MoviesView {

    private lateinit var presenter: MoviesPresenter

    override fun providePresenter(): BasePresenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        presenter = MoviesPresenterImpl(this)
    }

    override fun setupMoviesAdapter(movies: List<MovieListItem>?) {
        moviesRecyclerView.layoutManager = LinearLayoutManager(ApplicationContext.appContext)
        movies?.let {
            moviesRecyclerView.adapter = MoviesAdapter(movies)
        }
    }

    override fun navigateToDetailsActivity(movie: MovieListItem) {
        startActivity(Intent(this, DetailsActivity::class.java)
            .putExtra(IntentKeys.MOVIE_ID, movie.id)
            .putExtra(IntentKeys.MOVIE_TITLE, movie.title))
    }
}