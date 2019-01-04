package com.tsdproject.pokerplanning.service.api

import com.tsdproject.pokerplanning.model.transportobjects.AddUserTO
import com.tsdproject.pokerplanning.model.transportobjects.ReadyTokenTO
import com.tsdproject.pokerplanning.model.transportobjects.UserLoginTO
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface UsersApi {

    @POST("users/login")
    fun login(@Body userLogin: UserLoginTO): Observable<String>

    @POST("users/add")
    fun register(@Body addUser: AddUserTO): Observable<Void>

    @POST("users/setreadystatus")
    fun setTableReadyStatus(@Body readyToken: ReadyTokenTO): Observable<Void>
}