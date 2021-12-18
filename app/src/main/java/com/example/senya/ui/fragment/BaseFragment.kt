package com.example.senya.ui.fragment

import androidx.fragment.app.Fragment
import com.example.senya.viewModel.AttractionsViewModel

abstract class BaseFragment : Fragment() {

    protected val navController by lazy {
        (activity as MainActivity).navController
    }
    protected val activityViewModel: AttractionsViewModel
        get() = (activity as MainActivity).viewModel

}