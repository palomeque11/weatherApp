package com.example.viewpagertest.model.remote

//api.openweathermap.org/data/2.5/weather?zip={zip code},{country code}&appid={API key}
//api.openweathermap.org/data/2.5/weather?zip=30339&appid=c46d73490382f51c2389fc0823852113

//https://api.openweathermap.org/data/2.5/forecast?zip=30339&units=imperial&appid=c46d73490382f51c2389fc0823852113
//https://api.openweathermap.org/data/2.5/forecast?zip=30339&appid=c46d73490382f51c2389fc0823852113
const val FAHRENHEIT = "Fahrenheit"
const val CELSIUS = "Celsius"
const val FAHRENHEIT_UNITS = "imperial"
const val CELSIUS_UNITS = "metric"

const val LIMIT_TEMP:Double = 60.0


const val END_POINT = "data/2.5/weather"
const val BASE_URL = "https://api.openweathermap.org/"

const val END_POINT_HOURLY = "data/2.5/forecast"

const val ZIP = "zip"
const val APPID = "appid"
const val UNITS = "units"
const val KEYID = "c46d73490382f51c2389fc0823852113"
