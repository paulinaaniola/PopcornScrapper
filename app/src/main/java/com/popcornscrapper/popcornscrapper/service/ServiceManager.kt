package com.popcornscrapper.popcornscrapper.service

import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action0
import rx.functions.Action1
import rx.schedulers.Schedulers

object ServiceManager {


    private fun setupRequest(
        observable: Observable<*>?,
        onNext: Action1<Any>,
        onError: Action1<Throwable>,
        onCompleted: Action0
    ): Subscription? {
        return observable
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(onNext, onError, onCompleted)
    }
}