package com.popcornscrapper.popcornscrapper

import android.text.TextUtils

class ValidationUtil {

    companion object {
        fun checkIfValid(text: String, regex: Regex): Boolean {
            return !(TextUtils.isEmpty(text) || !text.trim { it <= ' ' }.matches(regex.regex.toRegex()))
        }
    }

    enum class Regex(var regex: String) {
        PHONE_NUMBER("(\\(?\\+[\\d]{2}\\(?)?([ .-]?)([0-9]{3})([ .-]?)([0-9]{3})\\4([0-9]{3})$"),
        ZIP_CODE("[0-9]{2}-[0-9]{3}"),
        EMAIL("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$"),
        EMPTY("(?s).*"),
        FIRST_NAME("^[\\p{L}\\s'.-]+\$"),
        LAST_NAME("^[\\p{L}\\s'-]+$")
    }
}
