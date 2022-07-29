package com.example.jeffreycarrion_tempsearch.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.jeffreycarrion_tempsearch.databinding.FragmentCitySearchBinding

class CitySearchFragment: ViewModelFragment() {

    private lateinit var binding: FragmentCitySearchBinding
    private lateinit var tempUnits: String



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCitySearchBinding.inflate(layoutInflater)


        binding.btnSearchCity.setOnClickListener{
            viewModel.setLoading()

            tempUnits = when(binding.rgTempUnit.checkedRadioButtonId){
                binding.rbK.id-> "standard"
                binding.rbF.id -> "imperial"

                else -> "metric"
            }


            findNavController().navigate(
              CitySearchFragmentDirections.actionCitySearchToCityTemp(
                  tempUnits,
                  binding.etSearchCity.text.toString()
              )
            )
        }

        return  binding.root
    }




}