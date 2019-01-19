package com.popcornscrapper.popcornscrapper.service

import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieImdbTO
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieListItem
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieMetacriticItemTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable


interface Api {

    @GET(("/search/{searchedTitle}"))
    fun getListOfMovies(@Path("searchedTitle") searchedTitle: String): Observable<List<MovieListItem>>

    @GET(("/title/{id}"))
    fun getImdbDetails(@Path("id") id: String): Observable<List<MovieImdbTO>>

    @GET(("/metacritic-rating/{title}"))
    fun getMetacriticRating(@Path("title") searchedTitle: String): Observable<List<MovieMetacriticItemTO>>
}