package com.popcornscrapper.popcornscrapper.service

import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieImdbTO
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieListItem
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieMetacriticItemTO
import com.popcornscrapper.popcornscrapper.service.receivers.GetImdbDetailsReceiver
import com.popcornscrapper.popcornscrapper.service.receivers.GetMetacriticRatingReceiver
import com.popcornscrapper.popcornscrapper.service.receivers.GetMoviesReceiver
import retrofit2.HttpException
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action0
import rx.functions.Action1
import rx.schedulers.Schedulers
import timber.log.Timber

object ServiceManager {


    private fun setupRequest(
        observable: Observable<*>?,
        onNext: Action1<Any>,
        onError: Action1<Throwable>,
        onCompleted: Action0 = Action0 { Timber.e("onCompleted") }
    ): Subscription? {
        return observable
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(onNext, onError, onCompleted)
    }

    fun getListOfMovies(
        receiver: GetMoviesReceiver,
        searchedTitle: String
    ) {
        setupRequest(ServiceProvider
            .moviesService
            ?.getListOfMovies(searchedTitle),
            Action1 { receiver.onGetMoviesSuccess(it as List<MovieListItem>) },
            Action1 { receiver.onGetMovieError() }
        )
    }

    fun getImdbDetails(
        receiver: GetImdbDetailsReceiver,
        movieId: String
    ) {
        setupRequest(ServiceProvider
            .moviesService
            ?.getImdbDetails(movieId),
            Action1 { receiver.onGetImdbDetailsSuccess(it as List<MovieImdbTO>) },
            Action1 { e ->
                receiver.onGetImdbDetailsError()
            }        )
    }

    fun getMetacriticRating(
        receiver: GetMetacriticRatingReceiver,
        searchedTitle: String
    ) {
        setupRequest(ServiceProvider
            .moviesService
            ?.getMetacriticRating(searchedTitle),
            Action1 { receiver.onGetMetacriticRatingSuccess(it as MovieMetacriticItemTO) },
            Action1 { receiver.onGetMetacriticRatingError() }
        )
    }
}