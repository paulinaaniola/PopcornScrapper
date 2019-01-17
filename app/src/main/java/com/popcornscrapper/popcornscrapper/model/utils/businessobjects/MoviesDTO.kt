package com.popcornscrapper.popcornscrapper.model.utils.businessobjects

import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieListItem
import java.io.Serializable

data class MoviesDTO(
    val movies: List<MovieListItem>
) : Serializable