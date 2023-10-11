package com.prueba.flows.main.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.prueba.databinding.ItemLocationBinding
import com.prueba.flows.main.views.models.LocationObj

class LocationAdapter(private val activities: ArrayList<LocationObj>) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    private lateinit var binding: ItemLocationBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val day = binding.textViewDay
        val hour = binding.textViewHour
        val lat = binding.textViewLat
        val lon = binding.textViewLon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemLocationBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: LocationAdapter.ViewHolder, position: Int) {
        val location = activities[position]
        holder.apply {
            day.text = location.dateDay
            hour.text = location.dateHour
            lat.text = location.latitude
            lon.text = location.longitude
        }
    }

    fun updateAdapter(list: ArrayList<LocationObj>) {
        activities.clear()
        activities.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return activities.size
    }
}