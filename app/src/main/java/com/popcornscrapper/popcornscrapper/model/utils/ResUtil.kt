package com.popcornscrapper.popcornscrapper

import android.graphics.drawable.Drawable

object ResUtil {

    fun getString(resourceId: Int): String {
        return ApplicationContext.appContext.getString(resourceId)
    }

    fun getDrawable(resourceId: Int): Drawable {
        return ApplicationContext.appContext.getDrawable(resourceId)
    }

    fun getColor(resourceId: Int): Int {
        return ApplicationContext.appContext.resources.getColor(resourceId)
    }
}
