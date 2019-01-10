package com.popcornscrapper.popcornscrapper.movies

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.popcornscrapper.popcornscrapper.ApplicationContext
import com.popcornscrapper.popcornscrapper.BaseActivity
import com.popcornscrapper.popcornscrapper.BasePresenter
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.Movie
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
        moviesRecyclerView.layoutManager = LinearLayoutManager(ApplicationContext.appContext)
        moviesRecyclerView.adapter = MoviesAdapter(getMovies())
    }

    fun getMovies(): List<Movie> {
        return listOf(Movie("Birdman", "1999", ""), Movie("Spider-man", "2001", ""))
    }
}