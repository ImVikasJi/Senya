package com.example.senya.ui.fragment

import androidx.fragment.app.Fragment
import com.example.senya.data.Attraction

abstract class BaseFragment : Fragment() {

    protected val navController by lazy {
        (activity as MainActivity).navController
    }
    protected val attractions : List<Attraction> get() = (activity as MainActivity).attractionList
}