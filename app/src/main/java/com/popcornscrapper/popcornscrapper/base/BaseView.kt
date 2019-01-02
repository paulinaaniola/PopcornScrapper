package com.popcornscrapper.popcornscrapper

import android.content.Context

interface BaseView {

    val activityContext: Context?

    fun startProgressDialog(message: String?)

    fun stopProgressDialog()

    fun performOnBackPressed()

    fun showToast(information: String)
}
