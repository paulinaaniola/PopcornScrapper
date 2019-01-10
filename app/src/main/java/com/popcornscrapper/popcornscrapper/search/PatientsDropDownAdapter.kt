package com.popcornscrapper.popcornscrapper.search

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.popcornscrapper.popcornscrapper.R
import com.popcornscrapper.popcornscrapper.model.utils.views.CustomDropDownAdapter
import kotlinx.android.synthetic.main.dialog_drop_down_patient.view.*

class PatientsDropDownAdapter internal constructor(
    context: Context?
) : CustomDropDownAdapter(context, R.layout.dialog_drop_down_patient) {

    private var movies: MutableList<String> = mutableListOf()

    override fun getItem(position: Int): Any? = movies[position]

    override fun getCount() = movies.size

    override fun getCustomView(position: Int, dropDownItem: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val retView = inflater.inflate(R.layout.dialog_drop_down_patient, parent, false)
        retView.movieNameTextView.text = movies[position]

        if (position == count - 1) {
            retView.grayLine.visibility = View.GONE
        }
        return retView
    }

    override fun dropDownItemOnClick(dropDownItem: Any) {
        autocompleteDropdown.dismissDropDown()
    }

    override fun setDropDownAdapterItems(dropDownItems: MutableList<String>) {
        this.movies.clear()
        this.movies = dropDownItems.toMutableList()
        notifyDataSetChanged ()
    }

    override fun clearDropDownAdapterItems() {
        if (movies.isNotEmpty()) {
            movies.clear()
            notifyDataSetChanged()
        }
    }

    override fun handleDropDownSearchRequest(
        editable: Editable?,
        textInputLayout: TextInputLayout?
    ) {

    }
}