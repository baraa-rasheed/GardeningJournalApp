package com.example.gardeningjournalapp

import android.app.Application
import androidx.lifecycle.LiveData

class PlantRepository(application: Application) {
    private val plantDao: PlantDao
    val allPlants: LiveData<List<Plant>>

    init {
        val database = AppDatabase.getDatabase(application)
        plantDao = database.plantDao()
        allPlants = plantDao.getAllPlants()
    }

    suspend fun insert(plant: Plant) {
        plantDao.insertPlant(plant)
    }

    suspend fun update(plant: Plant) {
        plantDao.update(plant)
    }

    suspend fun delete(plant: Plant) {
        plantDao.delete(plant.id)
    }

    fun getPlantById(plantId: Int): LiveData<Plant> {
        return plantDao.getPlantById(plantId)
    }
}
