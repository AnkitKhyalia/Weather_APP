package com.example.weather_app.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast?hourly=temperature_2m,weathercode,relative_humidity_2m,wind_speed_10m,pressure_msl")
    suspend fun getWeatherData(
        @Query("latitude")lat:Double,
        @Query("longitude") long:Double

    ):WeatherDto
}