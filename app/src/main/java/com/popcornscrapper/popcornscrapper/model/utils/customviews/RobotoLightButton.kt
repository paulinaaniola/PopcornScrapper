package com.popcornscrapper.popcornscrapper

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.Button

class RobotoLightButton : Button {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        if (!isInEditMode) {
            typeface = Typeface.createFromAsset(context.assets, "fonts/Roboto-Light.ttf")
            text = text.toString().toUpperCase()
            transformationMethod = null
        }
    }
}

