package com.example.jeffreycarrion_tempsearch.api

import com.example.jeffreycarrion_tempsearch.model.TempResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


//https://api.openweathermap.org/data/2.5/
// forecast?q={city}&appid=861d9c559c1069e8cd8093e3c0f00a8e

interface TempService {

    @GET("data/2.5/forecast")
    suspend fun getTempByCity(
        @Query("q") city: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ): Response<TempResponse>
}