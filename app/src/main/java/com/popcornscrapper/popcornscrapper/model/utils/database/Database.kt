package com.popcornscrapper.popcornscrapper.model.utils.database

import io.paperdb.Paper
import java.util.*


class Database {

    fun getRecentMovies(): LinkedList<String>? {
        val recentMovies = Paper.book().read<Any?>(DatabaseKeys.RECENT_MOVIES.toString())
        return recentMovies as LinkedList<String>?
    }

    fun putRecentMovies(recentMovies: LinkedList<String>) {
        Paper.book().write(DatabaseKeys.RECENT_MOVIES.toString(), recentMovies)
    }
}