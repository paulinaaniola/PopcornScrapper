package com.popcornscrapper.popcornscrapper.service

object ServiceProvider {

    var SERVICE_ENDPOINT: String = "http://13.81.106.52"

    var moviesService: Api? = null
        get() = if (field == null) ServiceFactory.createRetrofitService(
            Api::class.java,
            SERVICE_ENDPOINT
        ) else field

}

