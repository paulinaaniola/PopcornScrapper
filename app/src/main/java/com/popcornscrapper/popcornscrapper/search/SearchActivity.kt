package com.popcornscrapper.popcornscrapper.search

import android.graphics.Typeface
import android.os.Bundle
import com.popcornscrapper.popcornscrapper.ApplicationContext
import com.popcornscrapper.popcornscrapper.ApplicationContext.Companion.appContext
import com.popcornscrapper.popcornscrapper.BaseActivity
import com.popcornscrapper.popcornscrapper.BasePresenter
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.model.utils.database.Database
import com.popcornscrapper.popcornscrapper.model.utils.views.AutocompleteDropDownLayout
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*

class SearchActivity : BaseActivity(), SearchView {

    private lateinit var presenter: SearchPresenterImpl
    private var dropDownLayout: AutocompleteDropDownLayout? = null

    override fun providePresenter(): BasePresenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        presenter = SearchPresenterImpl(this)
        appContext = applicationContext
        setupFonts()
        setupDropDown()
        setupSearchButton()
    }

    private fun setupFonts() {
        findRatingsTextView.typeface = Typeface.createFromAsset(this.assets, "fonts/SourceCodePro-Regular.ttf")
    }

    private fun setupDropDown() {
        val recentMoviesAdapter = PatientsDropDownAdapter(ApplicationContext.appContext)
        dropDownLayout = AutocompleteDropDownLayout(ApplicationContext.appContext, dropDownFrameLayout)
        recentMoviesAdapter.autocompleteDropdown = (dropDownLayout as AutocompleteDropDownLayout).autocompleteTextView
        dropDownLayout?.setAdapter(recentMoviesAdapter)
        Database().getRecentMovies()?.let {
            recentMoviesAdapter.setDropDownAdapterItems(it)
        }
        dropDownFrameLayout.addView(dropDownLayout)
    }

    private fun setupSearchButton() {
        searchButton.setOnClickListener {
            val movie = dropDownLayout?.autocompleteTextView?.text.toString()
            if (movie.isNotEmpty()) addSearchedMovieToDatabase(movie)
        }
    }

    private fun addSearchedMovieToDatabase(newMovie: String) {
        var movies = Database().getRecentMovies()
        if (movies == null) {
            movies = LinkedList()
        }
        movies.push(newMovie)
        movies?.let {
            Database().putRecentMovies(movies)
        }
    }
}