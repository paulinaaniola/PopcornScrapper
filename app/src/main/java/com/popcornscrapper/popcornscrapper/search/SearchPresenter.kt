package com.popcornscrapper.popcornscrapper.search

import com.popcornscrapper.popcornscrapper.BasePresenter


interface SearchPresenter : BasePresenter {
    fun getMovies(title: String)
}