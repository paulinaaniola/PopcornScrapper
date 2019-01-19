package com.popcornscrapper.popcornscrapper.search

import com.popcornscrapper.popcornscrapper.BaseView
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieListItem

interface SearchView : BaseView {
    fun navigateToMoviesList(movies: List<MovieListItem>)
    fun clearSearchEditText()
    fun showNoResultsToast()
}