package com.tsdproject.pokerplanning.service.api

import com.tsdproject.pokerplanning.model.transportobjects.AnswerTokenTO
import com.tsdproject.pokerplanning.model.transportobjects.ReadyTokenTO
import com.tsdproject.pokerplanning.model.transportobjects.ResultTO
import com.tsdproject.pokerplanning.model.transportobjects.TokenTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

interface GamesApi {

    @POST("games/start")
    fun startGame(@Body Token: TokenTO): Observable<Void>

    @GET("games/isStarted")
    fun isGameStarted(@Query("token") Token: String?): Observable<Boolean>

    @POST("games/sendAnswer")
    fun sendAnswer(@Body Token: AnswerTokenTO): Observable<Void>

    @GET("games/getResults")
    fun getResults(@Query("token") Token: String?): Observable<List<ResultTO>>

    @POST("games/setReadyStatus")
    fun setGameReadyStatus(@Body readyToken: ReadyTokenTO): Observable<Void>

    @GET("games/isFinished")
    fun isGameFinished(@Query("token") token: String?): Observable<Boolean>
}