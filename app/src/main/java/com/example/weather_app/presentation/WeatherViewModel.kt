package com.example.weather_app.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_app.domain.location.LocationTracker
import com.example.weather_app.domain.repository.WeatherRepository
import com.example.weather_app.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
):ViewModel(){
    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo(){
        viewModelScope.launch {
            state = state.copy(
                isloading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when(val result = repository.getWeatherData(location.latitude,location.longitude)){
                    is Resource.Success ->{
                        state = state.copy(
                            weatherInfo = result.data,
                            isloading = false,
                            error = null
                        )
                    }
                    is Resource.Error ->{
                        state = state.copy(
                            weatherInfo = null,
                            isloading = false,
                            error = result.message
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isloading = false,
                    error = "Could not retrive location.Make Sure to grant permission and enable gps"
                )
            }

        }
    }


}