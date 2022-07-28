package com.example.jeffreycarrion_tempsearch.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jeffreycarrion_tempsearch.databinding.FragmentCityTempBinding
import com.example.jeffreycarrion_tempsearch.model.CityTemp
import com.example.jeffreycarrion_tempsearch.model.UIState


class CityTempFragment : ViewModelFragment() {
    private lateinit var binding: FragmentCityTempBinding
    private val weatherAdapter by lazy {
        WeatherAdapter(openTempDetail = ::openTempDetail)
    }
    private val args: CityTempFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityTempBinding.inflate(layoutInflater)


        configureObserver()
        return binding.root
    }

    private fun configureObserver() {

        viewModel.cityResultData.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Loading -> {
                    viewModel.getDemWeather(args.cityItem.toString(), args.tempUnits)

                }

                is UIState.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.tvLoadingText.text = uiState.errorMessage.message
                }

                is UIState.Success -> {
                    binding.apply {
                        titleCity.text = args.cityItem
                        pbLoading.visibility = View.GONE
                        tvLoadingText.visibility = View.GONE
                        weatherAdapter.setTempList(uiState.response.list)
                        rvCityResult.adapter = weatherAdapter

                    }

                    binding.btnBackMain.setOnClickListener {
                        findNavController().navigate(
                            CityTempFragmentDirections.actionBackToCitySearch()
                        )
                    }
                }
            }

        }
    }

    private fun openTempDetail(cityTemp: CityTemp){
        findNavController().navigate(
            CityTempFragmentDirections.actionCityTempToCityDetail(
                cityTemp,
                args.tempUnits,
                args.cityItem
            )
        )
    }



}