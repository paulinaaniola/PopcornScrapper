package com.popcornscrapper.popcornscrapper.model.utils.views

import android.content.Context
import android.graphics.Typeface
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.popcornscrapper.popcornscrapper.AutocompleteDropDown
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.model.utils.SizeUtils
import kotlinx.android.synthetic.main.dialog_drop_down_edit_text.view.*

class AutocompleteDropDownLayout : FrameLayout {

    private lateinit var textInputLayout: TextInputLayout
    lateinit var autocompleteTextView: AutocompleteDropDown
        private set

    constructor(context: Context?, parent: ViewGroup) : super(context) {
        init(parent)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, parent: ViewGroup) : super(context, attrs) {
        init(parent)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int,
        parent: ViewGroup
    ) : super(context, attrs, defStyleAttr) {
        init(parent)
    }

    fun init(parent: ViewGroup) {
        if (!isInEditMode) {
            textInputLayout = LayoutInflater.from(parent.context).inflate(
                R.layout.dialog_drop_down_edit_text,
                parent,
                false
            ) as TextInputLayout
            autocompleteTextView = textInputLayout.autocompleteTextView
            autocompleteTextView.setupDropDown()
            setupLayoutText()
            addView(textInputLayout)
            textInputLayout.setPadding(0, SizeUtils.dp2px(4f), 0, 0)
        }
    }

    private fun setupLayoutText() {
        textInputLayout.typeface = Typeface.createFromAsset(context.assets, "fonts/SourceCodePro-Regular.ttf")
    }

    override fun setId(id: Int) {
        textInputLayout.id = id
    }

    fun setHint(hint: String) {
        textInputLayout.hint = hint
    }

    fun setInputType(inputType: Int) {
        autocompleteTextView.inputType = inputType
    }

    fun setAdapter(adapter: CustomDropDownAdapter) {
        autocompleteTextView.setAdapter(adapter)
        autocompleteTextView.setupDropDownTextWatcher(adapter, textInputLayout)
    }
}