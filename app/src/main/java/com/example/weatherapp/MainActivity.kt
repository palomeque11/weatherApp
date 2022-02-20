package com.example.weatherapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.viewpagertest.model.remote.LIMIT_TEMP
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.remote.Degrees
import com.example.weatherapp.model.remote.HourlyResponse
import com.example.weatherapp.model.remote.WeatherResponse
import com.example.weatherapp.view.DailyAdapter
import com.example.weatherapp.view.SettingsActivity
import com.example.weatherapp.viewmodel.WeatherViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val ZIPCODE = "ZIPCODE"
const val DEGREES_CONST = "DEGREES"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by lazy {
        WeatherViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        binding.btnSettings.setOnClickListener { initSettings() }
        setContentView(binding.root)
    }

    private fun initSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        with(intent) {
            putExtra(ZIPCODE, viewModel.zipCode)
            putExtra(DEGREES_CONST, viewModel.degrees)
            startActivityForResult(intent, 1)
        }
    }

    override fun onStart() {
        super.onStart()
        if (viewModel.zipCode == 0) {
            initSettings()
        } else {
            requestData()
        }
    }

    private fun requestData() {
        viewModel.getCurrentWeather().enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        updateToolbar(it)
                    }
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            }
        })
        viewModel.getHourlyWeather().enqueue(object : Callback<HourlyResponse> {
            override fun onResponse(
                call: Call<HourlyResponse>,
                response: Response<HourlyResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("TAG", "onResponse: ${response.raw().request().url()}")
                        updateAdapter(it)
                    }
                }
            }

            override fun onFailure(call: Call<HourlyResponse>, t: Throwable) {
            }
        })
    }

    private fun updateToolbar(response: WeatherResponse) {
        with(binding) {
            this.tvMode.text = response.weather.firstOrNull()?.main
            this.tvCity.text = response.name
            this.tvNumber.text =
                binding.root.context.getString(
                    R.string.temp_format,
                    Math.ceil(response.main.temp.toDouble()).toInt().toString()
                )
            when (viewModel.degrees) {
                is Degrees.Fahrenheit -> {
                    setToolbarColor(response.main.temp.toDouble(), LIMIT_TEMP)
                }
                is Degrees.Celsius -> {
                    setToolbarColor(
                        response.main.temp.toDouble(),
                        ((LIMIT_TEMP - 32) * 5) / 9.0
                    )
                }
            }
            if (viewModel.degrees is Degrees.Fahrenheit) {

            } else {

            }
        }
    }

    fun setToolbarColor(temp: Double, limit: Double) {
        if (temp > limit) {
            binding.toolbar.setBackgroundColor(getResources().getColor(R.color.orange))
            binding.btnSettings.setBackgroundColor(getResources().getColor(R.color.orange))
        } else {
            binding.toolbar.setBackgroundColor(getResources().getColor(R.color.blue))
            binding.btnSettings.setBackgroundColor(getResources().getColor(R.color.blue))
        }
    }

    fun updateAdapter(dataSet: HourlyResponse) {
        binding.dailyResults.layoutManager = GridLayoutManager(this, 1)
        binding.dailyResults.adapter = DailyAdapter(dataSet)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            viewModel.setZipCode(data.getIntExtra(ZIPCODE, 0))
            data.getParcelableExtra<Degrees>(DEGREES_CONST)?.let { viewModel.setDegree(it) }
        }
    }

}