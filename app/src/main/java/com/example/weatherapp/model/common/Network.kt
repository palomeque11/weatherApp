package com.example.weatherapp.model.common

import com.example.viewpagertest.model.remote.BASE_URL
import com.example.weatherapp.model.remote.WeatherApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {
    val weatherApi: WeatherApi by lazy {
        initRetrofit().create(WeatherApi::class.java)
    }

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}