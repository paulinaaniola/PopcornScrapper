package com.popcornscrapper.popcornscrapper

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.KeyEvent
import android.widget.EditText
import android.widget.TextView

class RobotoLightEditText : EditText {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        if (!isInEditMode) {
            setTypefaces()
        }
        setMaxLinesInput(1)
    }

    private fun setTypefaces() {
        val tf: Typeface = Typeface.createFromAsset(context.assets, "fonts/Roboto-Light.ttf")
        typeface = tf

        val getLabelView = EditText::class.java.getDeclaredMethod("getLabelView")
        getLabelView.isAccessible = true
        (getLabelView.invoke(this) as TextView).typeface = tf

        val getSupportView = EditText::class.java.getDeclaredMethod("getSupportView")
        getSupportView.isAccessible = true
        (getSupportView.invoke(this) as TextView).typeface = tf
    }

    private fun setMaxLinesInput(maxLines: Int) {
        setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val text = text.toString()
                val editTextRowCount =
                    text.split("\\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().size
                if (editTextRowCount >= maxLines) {
                    val lastBreakIndex = text.lastIndexOf("\n")
                    if (lastBreakIndex >= 0) {
                        val newText = text.substring(0, lastBreakIndex)
                        setText("")
                        append(newText)
                    }
                }
            }
            false
        }
    }
}