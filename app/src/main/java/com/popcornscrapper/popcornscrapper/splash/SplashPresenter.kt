package com.popcornscrapper.popcornscrapper.splash

import com.popcornscrapper.popcornscrapper.BasePresenter


interface SplashPresenter : BasePresenter {
    fun handleSplashScreen(startActivityFunction: () -> Unit)
}