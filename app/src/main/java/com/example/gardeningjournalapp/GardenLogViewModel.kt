package com.example.gardeningjournalapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class GardenLogViewModel(application: Application) : AndroidViewModel(application) {
    val allPlants: LiveData<List<Plant>>
    private val plantRepository: PlantRepository

    init {
        plantRepository = PlantRepository(application)
        allPlants = plantRepository.allPlants
    }

    fun insertPlant(plant: Plant) {
        viewModelScope.launch {
            plantRepository.insert(plant)
        }
    }

    fun getPlantById(plantId: Int): LiveData<Plant> {
        return plantRepository.getPlantById(plantId)
    }

}
