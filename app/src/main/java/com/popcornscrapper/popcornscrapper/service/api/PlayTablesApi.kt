package com.tsdproject.pokerplanning.service.api

import com.tsdproject.pokerplanning.model.transportobjects.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

interface PlayTablesApi {

    @POST("playtables/create")
    fun createTable(@Body Token: TokenTO): Observable<String>

    @POST("playtables/join")
    fun joinTable(@Body userTableToken: UserTableTokenTO): Observable<Void>

    @GET("playtables/getParticipants")
    fun getParticipants(@Query("token") Token: String?): Observable<ParticipantsTO>

    @POST("playtables/kickParticipant")
    fun kickParticipants(@Body tokenAndEmail: TokenAndEmailTO): Observable<Void>

    @POST("playtables/setTaskName")
    fun setTaskName(@Body taskNameToken: TaskNameTokenTO): Observable<Void>
}