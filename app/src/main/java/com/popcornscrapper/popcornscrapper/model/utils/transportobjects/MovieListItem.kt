package com.popcornscrapper.popcornscrapper.model.utils.transportobjects

import java.io.Serializable

data class MovieListItem(
    val title: String?,
    val year: String?,
    val id: String?,
    val poster: String?
) : Serializable