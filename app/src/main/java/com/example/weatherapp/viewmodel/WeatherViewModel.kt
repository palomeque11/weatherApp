package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.viewpagertest.model.remote.CELSIUS_UNITS
import com.example.viewpagertest.model.remote.FAHRENHEIT_UNITS
import com.example.weatherapp.model.remote.Degrees
import com.example.weatherapp.model.common.Network
import com.example.weatherapp.model.remote.HourlyResponse
import com.example.weatherapp.model.remote.WeatherResponse
import retrofit2.Call

class WeatherViewModel : ViewModel() {

    private var _zipCode: Int = 0
    val zipCode: Int
        get() = _zipCode

    private var _degrees: Degrees = Degrees.Fahrenheit()
    val degrees: Degrees
        get() = _degrees

    fun getCurrentWeather(): Call<WeatherResponse> {
        when (_degrees) {
            is Degrees.Fahrenheit -> return Network.weatherApi.getCurrentWeather(
                zipCode.toString(),
                FAHRENHEIT_UNITS
            )
            is Degrees.Celsius -> return Network.weatherApi.getCurrentWeather(
                zipCode.toString(),
                CELSIUS_UNITS
            )
        }
    }
    fun getHourlyWeather(): Call<HourlyResponse> {
        when (_degrees) {
            is Degrees.Fahrenheit -> return Network.weatherApi.getHourlyWeather(
                zipCode.toString(),
                FAHRENHEIT_UNITS
            )
            is Degrees.Celsius -> return Network.weatherApi.getHourlyWeather(
                zipCode.toString(),
                CELSIUS_UNITS
            )
        }
    }

    fun setZipCode(zipCode: Int) {
        _zipCode = zipCode
    }

    fun setDegree(degree: Degrees) {
        _degrees = degree
    }
}

