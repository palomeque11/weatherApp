package com.example.weatherapp.model.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class WeatherResponse(val weather: List<StatusItem>, val main: TempItem, val name: String)

data class StatusItem(val main: String)

data class TempItem(val temp:Double)

sealed class Degrees(val nameType:String):Parcelable{
    @Parcelize
    data class Fahrenheit(val name:String = "Fahrenheit"): Degrees(name),Parcelable
    @Parcelize
    data class Celsius(val name:String = "Celsius"): Degrees(name),Parcelable
}
