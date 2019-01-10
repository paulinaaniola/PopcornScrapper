package com.popcornscrapper.popcornscrapper.model.utils.database

enum class DatabaseKeys constructor(private val key: String) {
    RECENT_MOVIES("recent_movies");

    override fun toString(): String {
        return key
    }
}