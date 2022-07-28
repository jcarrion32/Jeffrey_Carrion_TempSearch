package com.example.jeffreycarrion_tempsearch.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//https://api.openweathermap.org/data/2.5/forecast?q={city}&appid=861d9c559c1069e8cd8093e3c0f00a8e
/*
"list": [
        {
            "dt": 1658242800,
            "main": {
                **"temp": 301.61,
                **"feels_like": 305.05,
            },
            "weather": [
                {
                    "id": 800,
                   ** "main": "Clear",
                    **"description": "clear sky"
                }
            ],
            "clouds": {
                "all": 1
            },
            "dt_txt": "2022-07-19 15:00:00"
        }
 */

@Parcelize
data class TempResponse(
    val list: List<CityTemp>
): Parcelable

@Parcelize
data class CityTemp(
    val main: TempDesc,
    val weather: List<WeatherDesc>

):Parcelable

@Parcelize
data class TempDesc(
    val temp: Double,
    val feels_like: Double
):Parcelable

@Parcelize
data class WeatherDesc(
    val main: String,
    val description: String
): Parcelable
