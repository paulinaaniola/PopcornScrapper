package com.popcornscrapper.popcornscrapper.search

import android.graphics.Typeface
import android.os.Bundle
import com.popcornscrapper.popcornscrapper.BaseActivity
import com.popcornscrapper.popcornscrapper.BasePresenter
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.splash.SplashPresenter
import com.popcornscrapper.popcornscrapper.splash.SplashPresenterImpl
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_splash.*

class SearchActivity : BaseActivity(), SearchView {

    private lateinit var presenter: SearchPresenterImpl

    override fun providePresenter(): BasePresenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        presenter = SearchPresenterImpl(this)
        setupFonts()
    }

    private fun setupFonts(){
        findRatingsTextView.typeface = Typeface.createFromAsset(this.assets, "fonts/SourceCodePro-Regular.ttf")
    }
}
