package com.popcornscrapper.popcornscrapper

import android.content.Intent

interface BasePresenter {
    fun initExtras(intent: Intent)
}