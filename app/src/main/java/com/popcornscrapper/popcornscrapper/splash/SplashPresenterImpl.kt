package com.popcornscrapper.popcornscrapper.splash

import android.content.Intent
import android.os.Handler


class SplashPresenterImpl(var view: SplashView) : SplashPresenter {

    override fun initExtras(intent: Intent) {
    }

    override fun handleSplashScreen(startActivityFunction: () -> Unit) {
        Handler().postDelayed(startActivityFunction, 1500)
    }
}