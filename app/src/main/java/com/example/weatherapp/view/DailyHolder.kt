package com.example.weatherapp.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemLayoutBinding
import com.example.weatherapp.databinding.ItemTimeLayoutBinding
import com.example.weatherapp.model.remote.HourItem
import com.example.weatherapp.model.remote.HourlyResponse

class DailyHolder (private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: HourlyResponse, position:Int){
        when(position){
            0-> binding.tvDay.text = "Tomorrow"
            else -> {
                val split = item.list[position*7].dt_txt.split(" ")[0].split("-")
                binding.tvDay.text = split[1] + "-" + split[2]
            }
        }
        binding.hourResults.layoutManager = GridLayoutManager(binding.root.context, 4)
        binding.hourResults.adapter = HourAdapter(item,position)

    }
}

class HourAdapter(private val dataSet: HourlyResponse, private val pos:Int) : RecyclerView.Adapter<HourlyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HourlyHolder(ItemTimeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: HourlyHolder, position: Int) {
        holder.onBind(dataSet.list[position + (pos * 7)])
    }
    override fun getItemCount(): Int = 7

}

class HourlyHolder (private val binding: ItemTimeLayoutBinding): RecyclerView.ViewHolder(binding.root){
    fun onBind(hourItem: HourItem) {
        val split = hourItem.dt_txt.split(" ")[1].split(":")
        if(split[0].toInt()>12){
            binding.tvDay.text = "0"+(split[0].toInt()-12).toString() + ":" + split[1] + " PM"
        }else{
            binding.tvDay.text = split[0] + ":" + split[1] + " AM"
        }
        binding.tvTempNumber.text =
            binding.root.context.getString(
                R.string.temp_format,
                Math.ceil(hourItem.main.temp.toDouble()).toInt().toString()
            )
        when(hourItem.weather.firstOrNull()?.main){
            "Clouds" ->  binding.ivCover.setImageResource(R.drawable.ic_baseline_cloud_queue_24)
            "Clear" ->  binding.ivCover.setImageResource(R.drawable.ic_outline_wb_sunny_24)
            "Rain" ->  binding.ivCover.setImageResource(R.drawable.rain)
        }
    }

}
