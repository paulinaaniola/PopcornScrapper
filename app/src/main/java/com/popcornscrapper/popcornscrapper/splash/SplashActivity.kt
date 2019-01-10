package com.popcornscrapper.popcornscrapper.splash

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import com.popcornscrapper.popcornscrapper.BaseActivity
import com.popcornscrapper.popcornscrapper.BasePresenter
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.search.SearchActivity
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity(), SplashView {

    private lateinit var presenter: SplashPresenter

    override fun providePresenter(): BasePresenter? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenterImpl(this)
        setupLogoFont()
        setupNextViewDelay()
        Paper.init(applicationContext)
    }

    override fun setupNextViewDelay() {
        presenter.handleSplashScreen { startActivity(Intent(this, SearchActivity::class.java)) }
    }

    private fun setupLogoFont() {
        appNameTextView.typeface = Typeface.createFromAsset(this.assets, "fonts/SourceCodePro-Regular.ttf")
    }
}
