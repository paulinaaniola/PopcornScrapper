package com.popcornscrapper.popcornscrapper.search

import android.graphics.Typeface
import android.os.Bundle
import com.popcornscrapper.popcornscrapper.ApplicationContext
import com.popcornscrapper.popcornscrapper.ApplicationContext.Companion.appContext
import com.popcornscrapper.popcornscrapper.BaseActivity
import com.popcornscrapper.popcornscrapper.BasePresenter
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.model.utils.views.AutocompleteDropDownLayout
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
        appContext = applicationContext
        setupFonts()
        setupAdapter()
    }

    private fun setupFonts() {
        findRatingsTextView.typeface = Typeface.createFromAsset(this.assets, "fonts/SourceCodePro-Regular.ttf")
    }

    private fun setupAdapter(){
        val recentMoviesAdapter = PatientsDropDownAdapter(ApplicationContext.appContext)
        val dropDownLayout = AutocompleteDropDownLayout(ApplicationContext.appContext, contentLinearLayout)
        recentMoviesAdapter.autocompleteDropdown = dropDownLayout.autocompleteTextView
        dropDownLayout.setAdapter(recentMoviesAdapter)
        recentMoviesAdapter.setDropDownAdapterItems(listOf("Birdman", "Spider Man", "Kleksjuuu"))
        contentLinearLayout.addView(dropDownLayout)
    }
}
