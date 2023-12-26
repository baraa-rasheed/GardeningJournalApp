package com.example.gardeningjournalapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gardeningjournalapp.databinding.ItemPlantBinding

class PlantAdapter : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {
    private var plantList: List<Plant> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPlantBinding.inflate(inflater, parent, false)
        return PlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val currentPlant = plantList[position]
        holder.bind(currentPlant)
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    fun setPlantList(plants: List<Plant>) {
        plantList = plants
        notifyDataSetChanged()
    }

    inner class PlantViewHolder(private val binding: ItemPlantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(plant: Plant) {
            binding.textViewPlantName.text = plant.name
            binding.textViewPlantType.text = plant.type
            binding.textViewWateringFrequency.text = "$plant.wateringFrequency"
            binding.textViewPlantingDate.text = plant.plantingDate
        }
    }
}
