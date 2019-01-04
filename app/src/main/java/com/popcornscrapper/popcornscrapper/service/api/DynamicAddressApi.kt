package com.tsdproject.pokerplanning.service.api

import retrofit2.http.GET
import rx.Observable

interface DynamicAddressApi {

    @GET("tsd-ip.txt")
    fun getDynamicAddress(): Observable<String>
}