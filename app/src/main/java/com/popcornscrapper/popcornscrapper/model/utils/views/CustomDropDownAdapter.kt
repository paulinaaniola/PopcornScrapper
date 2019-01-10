package com.popcornscrapper.popcornscrapper.model.utils.views

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import java.util.*

abstract class CustomDropDownAdapter internal constructor(
    context: Context?,
    itemLayoutId: Int
) : ArrayAdapter<Any>(context, itemLayoutId, ArrayList()) {

    private var dropDownItems: List<Any> = ArrayList()
    lateinit var autocompleteDropdown: AutoCompleteTextView
    lateinit var parent: ViewGroup

    override fun getItem(position: Int): Any? = dropDownItems[position]

    override fun getCount() = dropDownItems.size

    override fun getView(position: Int, dropDownItem: View?, parent: ViewGroup): View {
        return getCustomView(position, dropDownItem, parent)
    }

    abstract fun setDropDownAdapterItems(dropDownItems: List<String>)

    abstract fun dropDownItemOnClick(dropDownItem: Any)

    abstract fun getCustomView(position: Int, dropDownItem: View?, parent: ViewGroup): View

    abstract fun handleDropDownSearchRequest(editable: Editable?, textInputLayout: TextInputLayout?)

    abstract fun clearDropDownAdapterItems()
}