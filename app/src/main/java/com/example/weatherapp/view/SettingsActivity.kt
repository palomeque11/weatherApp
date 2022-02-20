package com.example.weatherapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.viewpagertest.model.remote.CELSIUS
import com.example.viewpagertest.model.remote.FAHRENHEIT
import com.example.weatherapp.DEGREES_CONST
import com.example.weatherapp.R
import com.example.weatherapp.ZIPCODE
import com.example.weatherapp.databinding.ActivitySettingsBinding
import com.example.weatherapp.model.remote.Degrees


class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        intent.getIntExtra(ZIPCODE,0).apply {
            if(this>0){
                binding.ietZipCode.setText(this.toString())
            }
        }
        binding.spDegrees.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.spinnerData)
        )
        when(intent.getParcelableExtra<Degrees>(DEGREES_CONST)){
            is Degrees.Fahrenheit -> binding.spDegrees.setSelection(0)
            is Degrees.Celsius -> binding.spDegrees.setSelection(1)
        }
        binding.btnBack.setOnClickListener{sendDataBack()}
    }

    fun sendDataBack() {
        val sendData = Intent()
        sendData.putExtra(ZIPCODE, binding.ietZipCode.text.toString().toInt())
        Log.d("Degrees", "onCreate: ${binding.spDegrees.selectedItem.toString()}")
        when(binding.spDegrees.selectedItem.toString()){
            FAHRENHEIT->  sendData.putExtra(DEGREES_CONST, Degrees.Fahrenheit())
            CELSIUS ->  sendData.putExtra(DEGREES_CONST, Degrees.Celsius())
        }
        setResult(RESULT_OK, sendData)
        finish()
    }
}