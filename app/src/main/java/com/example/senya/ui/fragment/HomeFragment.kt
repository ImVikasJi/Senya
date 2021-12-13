package com.example.senya.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.senya.adapter.HomeFragmentAdapter
import com.example.senya.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeAdapter = HomeFragmentAdapter(){
            //to handle item being clicked
        }
        binding.rvRecyclerView.adapter = homeAdapter
        binding.rvRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(),RecyclerView.VERTICAL))
        homeAdapter.setData(attractions)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}