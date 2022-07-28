package com.example.jeffreycarrion_tempsearch.views

import androidx.fragment.app.Fragment
import com.example.jeffreycarrion_tempsearch.di.DI

open class ViewModelFragment: Fragment() {

    protected val viewModel by lazy {
        DI.providerViewModel(requireActivity())
    }
}