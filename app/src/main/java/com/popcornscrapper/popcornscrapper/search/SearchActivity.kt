package com.popcornscrapper.popcornscrapper.search

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import com.popcornscrapper.popcornscrapper.ApplicationContext
import com.popcornscrapper.popcornscrapper.ApplicationContext.Companion.appContext
import com.popcornscrapper.popcornscrapper.BaseActivity
import com.popcornscrapper.popcornscrapper.BasePresenter
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.model.utils.IntentKeys
import com.popcornscrapper.popcornscrapper.model.utils.businessobjects.MoviesDTO
import com.popcornscrapper.popcornscrapper.model.utils.database.Database
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieListItem
import com.popcornscrapper.popcornscrapper.model.utils.views.AutocompleteDropDownLayout
import com.popcornscrapper.popcornscrapper.movies.MoviesActivity
import kotlinx.android.synthetic.main.activity_search.*
import timber.log.Timber
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
        val recentMoviesAdapter = MoviesDropDownAdapter(ApplicationContext.appContext)
        dropDownLayout = AutocompleteDropDownLayout(ApplicationContext.appContext, dropDownFrameLayout)
        recentMoviesAdapter.autocompleteDropdown = (dropDownLayout as AutocompleteDropDownLayout).autocompleteTextView
        dropDownLayout?.setAdapter(recentMoviesAdapter)
        Database().getRecentMovies()?.let {
            if (it.size > 5) {
                recentMoviesAdapter.setDropDownAdapterItems(it.subList(0, 4))
            } else {
                recentMoviesAdapter.setDropDownAdapterItems(it)
            }
        }
        dropDownFrameLayout.addView(dropDownLayout)
    }

    private fun setupSearchButton() {
        searchButton.setOnClickListener {
            onSearchButtonClick()
        }
    }

    private fun onSearchButtonClick() {
        val searchedTitle = dropDownLayout?.autocompleteTextView?.text.toString()
        if (searchedTitle.isNotEmpty()) addSearchedMovieToDatabase(searchedTitle)
        presenter.getMovies(searchedTitle)
    }

    private fun addSearchedMovieToDatabase(newMovie: String) {
        var movies = Database().getRecentMovies()
        if (movies == null) {
            Database().putRecentMovies(LinkedList(listOf(newMovie)))
        } else if (!(movies.contains(newMovie) || movies.contains(newMovie.toLowerCase()))) {
            movies.remove(newMovie)
            movies.push(newMovie)
            Database().putRecentMovies(movies)
        }
    }

    override fun navigateToMoviesList(movies: List<MovieListItem>) {
        val bundle: Bundle = Bundle().apply { putSerializable(IntentKeys.MOVIES, MoviesDTO(movies)) }
        startActivity(Intent(this, MoviesActivity::class.java).putExtras(bundle))
    }

    override fun clearSearchEditText(){
        dropDownLayout?.autocompleteTextView?.text?.clear()
    }

    override fun showNoResultsToast(){
        showToast(getString(R.string.no_results_toast))
    }
}
