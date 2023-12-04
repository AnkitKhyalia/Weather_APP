package com.example.weather_app.presentation

import com.example.weather_app.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? =null,
    val isloading:Boolean = false,
    var error: String ?=null
)
