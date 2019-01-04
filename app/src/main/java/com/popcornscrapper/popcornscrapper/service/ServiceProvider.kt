package com.tsdproject.pokerplanning.service

import com.tsdproject.pokerplanning.service.api.DynamicAddressApi
import com.tsdproject.pokerplanning.service.api.GamesApi
import com.tsdproject.pokerplanning.service.api.PlayTablesApi
import com.tsdproject.pokerplanning.service.api.UsersApi

object ServiceProvider {

    var SERVICE_ENDPOINT: String = ""
    private const val DYNAMIC_ADDRESS_ENDPOINT = "https://sandbox27.neocities.org/"

    var dynamicAddressService: DynamicAddressApi? = null
        get() = if (field == null) ServiceFactory.createRetrofitService(
            DynamicAddressApi::class.java,
            DYNAMIC_ADDRESS_ENDPOINT
        ) else field

    var usersService: UsersApi? = null
        get() = if (field == null) ServiceFactory.createRetrofitService(
            UsersApi::class.java,
            SERVICE_ENDPOINT
        ) else field

    var playTablesService: PlayTablesApi? = null
        get() = if (field == null) ServiceFactory.createRetrofitService(
            PlayTablesApi::class.java,
            SERVICE_ENDPOINT
        ) else field

    var gamesService: GamesApi? = null
        get() = if (field == null) ServiceFactory.createRetrofitService(
            GamesApi::class.java,
            SERVICE_ENDPOINT
        ) else field
}

