package com.popcornscrapper.popcornscrapper.service

import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieImdbTO
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieListItem
import com.popcornscrapper.popcornscrapper.model.utils.transportobjects.MovieMetacriticItemTO
import com.popcornscrapper.popcornscrapper.service.receivers.GetMovieDetailsReceiver
import com.popcornscrapper.popcornscrapper.service.receivers.GetMoviesReceiver
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

    fun getMovieDetails(
        receiver: GetMovieDetailsReceiver,
        movieId: String,
        movieTitle: String
    ) {
        var imdbDetails: MovieImdbTO? = null
        var metacriticRating: MovieMetacriticItemTO? = null
        setupRequest(
            Observable.merge(
                ServiceProvider.moviesService?.getImdbDetails(movieId),
                ServiceProvider.moviesService?.getMetacriticRating(movieTitle)
            ),
            Action1 {
                (it as? List<*>)?.let { responseList ->
                    if (responseList.isNotEmpty() && responseList[0] is MovieImdbTO) {
                        imdbDetails = responseList[0] as MovieImdbTO
                    } else if (responseList.isNotEmpty()) {
                        metacriticRating = responseList[0] as MovieMetacriticItemTO
                    }
                }
            },
            Action1
            { e ->
                receiver.onGetDetailsError()
            },
            Action0
            { receiver.onGetDetailsSuccess(imdbDetails, metacriticRating) })

    }
}