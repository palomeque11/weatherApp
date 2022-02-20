package com.example.weatherapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemLayoutBinding
import com.example.weatherapp.model.remote.HourlyResponse

class DailyAdapter(
    private val dataSet: HourlyResponse
) : RecyclerView.Adapter<DailyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DailyHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: DailyHolder, position: Int) {
        holder.onBind(dataSet,position)
    }
    override fun getItemCount(): Int = dataSet.list.size/(23/3)

}