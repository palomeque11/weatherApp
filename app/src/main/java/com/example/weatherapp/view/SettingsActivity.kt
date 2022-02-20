package com.example.weatherapp.view

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.viewpagertest.model.remote.CELSIUS
import com.example.viewpagertest.model.remote.FAHRENHEIT
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivitySettingsBinding
import com.example.weatherapp.model.remote.Degrees
import com.example.weatherapp.viewModel


class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        if (viewModel.zipCode > 0) {
            binding.ietZipCode.setText(viewModel.zipCode.toString())
        }
        binding.spDegrees.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.spinnerData)
        )
        when (viewModel.degrees) {
            is Degrees.Fahrenheit -> binding.spDegrees.setSelection(0)
            is Degrees.Celsius -> binding.spDegrees.setSelection(1)
        }
        binding.btnBack.setOnClickListener { sendDataBack() }
    }

    fun sendDataBack() {
        viewModel.setZipCode(binding.ietZipCode.text.toString().toInt())
        when (binding.spDegrees.selectedItem.toString()) {
            FAHRENHEIT -> viewModel.setDegree(Degrees.Fahrenheit())
            CELSIUS -> viewModel.setDegree(Degrees.Celsius())
        }
        finish()
    }
}