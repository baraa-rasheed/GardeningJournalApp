package com.example.gardeningjournalapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gardeningjournalapp.databinding.FragmentGardenLogBinding
import com.example.gardeningjournalapp.databinding.FragmentHomeBinding

class GardenLogFragment : Fragment() {

    private lateinit var viewModel: GardenLogViewModel
    private lateinit var plantAdapter: PlantAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGardenLogBinding.inflate(inflater, container, false)
        plantAdapter = PlantAdapter()
        binding.recyclerViewPlants.adapter = plantAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(GardenLogViewModel::class.java)

        val samplePlants = mutableListOf<Plant>()

// Add sample plants
        samplePlants.add(Plant(name = "Rose", type = "Flower", wateringFrequency = 2, plantingDate = "2023-01-01"))
        samplePlants.add(Plant(name = "Tomato", type = "Vegetable", wateringFrequency = 3, plantingDate = "2023-02-15"))
        samplePlants.add(Plant(name = "Basil", type = "Herb", wateringFrequency = 1, plantingDate = "2023-03-10"))

        // Insert sample plants into the database
        for (plant in samplePlants) {
            viewModel.insertPlant(plant)
        }


        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(GardenLogViewModel::class.java)

        // Observe LiveData object for plant list
        viewModel.allPlants.observe(viewLifecycleOwner, { plants ->
            plantAdapter.setPlantList(plants)
        })

        // Set up button or form to add new plants
        // Implement coroutine to insert plant data into the database
    }
}
