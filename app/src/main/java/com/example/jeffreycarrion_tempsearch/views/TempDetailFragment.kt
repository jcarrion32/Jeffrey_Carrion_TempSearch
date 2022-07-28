package com.example.jeffreycarrion_tempsearch.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jeffreycarrion_tempsearch.databinding.FragmentTempDetailBinding
import com.example.jeffreycarrion_tempsearch.model.UIState

class TempDetailFragment: ViewModelFragment() {

    private lateinit var binding: FragmentTempDetailBinding
    private val args: TempDetailFragmentArgs by navArgs()
    private lateinit var city: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTempDetailBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root

    }

    private fun configureObserver(){

        viewModel.cityResultData.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.Loading -> {
                }

                is UIState.Error -> {
                }

                is UIState.Success -> {
                    binding.apply {

                        tvCityTempDetail.text = args.detailItem?.main?.temp.toString()
                        tvFeelsLikeDetails.text = args.detailItem?.main?.feels_like.toString()

                        tvCloudTx.text = args.detailItem?.weather?.get(0)?.main
                        tvCloudDetail.text = args.detailItem?.weather?.get(0)?.description

                    }

//                    binding.ibBackCityTemp.setOnClickListener {
//                        findNavController().navigate(
//                            TempDetailFragmentDirections.actionBackToCityTemp()
//                        )
//                    }
                }
            }
        }
    }
}