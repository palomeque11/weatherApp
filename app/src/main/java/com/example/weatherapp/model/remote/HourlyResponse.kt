package com.example.weatherapp.model.remote

data class HourlyResponse (val list: List<HourItem>)

data class HourItem(val main:MainItem,val weather: List<StatusItem>,val dt_txt:String)

data class MainItem (val temp:Double)
