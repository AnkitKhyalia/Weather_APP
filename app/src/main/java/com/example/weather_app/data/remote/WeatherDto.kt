package com.example.weather_app.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class WeatherDto (

    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto


)