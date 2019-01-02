package com.popcornscrapper.popcornscrapper

import android.widget.EditText


object EditTextUtil {

    fun checkIfEditTextEmpty(editText: EditText): Boolean {
        val isEditTextEmpty = editText.text.isEmpty()
        if (isEditTextEmpty) {
            editText.error = ResUtil.getString(R.string.blank_edit_text_error)
        } else {
            editText.error = null
        }
        return isEditTextEmpty
    }
}