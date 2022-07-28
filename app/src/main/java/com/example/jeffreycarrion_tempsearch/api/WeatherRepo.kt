package com.example.jeffreycarrion_tempsearch.api

import com.example.jeffreycarrion_tempsearch.model.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface WeatherRepo {
    suspend fun getWeatherByCity(city: String, units: String): Flow<UIState>
}

class WeatherRepoImpl(private val service: TempService) : WeatherRepo {
    override suspend fun getWeatherByCity(city: String, units: String): Flow<UIState> =
        flow {
            try {
                val response = service.getTempByCity(city, "861d9c559c1069e8cd8093e3c0f00a8e", units)
                if (response.isSuccessful) {
                    emit(response.body()?.let {
                        UIState.Success(it)
                    }?: throw Exception("Empty Response"))
                }else throw Exception("Failed Network Call")
            }catch (e: Exception){
              emit(UIState.Error(e))
            }
        }
}