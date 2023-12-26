package com.example.gardeningjournalapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantDetailsFragment : Fragment() {
    private lateinit var viewModel: GardenLogViewModel
    private var plantId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_plant_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        plantId = arguments?.getInt("plantId") ?: 0

        viewModel = ViewModelProvider(this)[GardenLogViewModel::class.java]

        viewModel.getPlantById(plantId).observe(viewLifecycleOwner, Observer { plant ->
            plant?.let { displayPlantDetails(it) }
        })
    }

    private fun displayPlantDetails(plant: Plant) {
        view?.findViewById<TextView>(R.id.textViewPlantName)?.text = plant.name
        view?.findViewById<TextView>(R.id.textViewPlantType)?.text = "Type: ${plant.type}"
        view?.findViewById<TextView>(R.id.textViewWateringFrequency)?.text =
            "Watering Frequency: ${plant.wateringFrequency} days"
        view?.findViewById<TextView>(R.id.textViewPlantingDate)?.text =
            "Planting Date: ${plant.plantingDate}"
    }
}
