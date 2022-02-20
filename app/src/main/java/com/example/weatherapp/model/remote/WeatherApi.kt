package com.example.weatherapp.model.remote

import com.example.viewpagertest.model.remote.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(END_POINT)
    fun getCurrentWeather(
        @Query(ZIP) zip: String,
        @Query(UNITS) units: String,
        @Query(APPID) appid: String = KEYID
    ): Call<WeatherResponse>

    @GET(END_POINT_HOURLY)
    fun getHourlyWeather(
        @Query(ZIP) zip: String,
        @Query(UNITS) units: String,
        @Query(APPID) appid: String = KEYID
    ): Call<HourlyResponse>

}