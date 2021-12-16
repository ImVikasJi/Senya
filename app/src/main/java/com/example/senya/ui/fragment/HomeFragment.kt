package com.example.senya.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.senya.R
import com.example.senya.adapter.HomeFragmentAdapter
import com.example.senya.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

        val homeAdapter = HomeFragmentAdapter{ attractionId ->
            activityViewModel.onAttractionSelected(attractionId)
            navController.navigate(R.id.action_homeFragment_to_attractionDetailFragment)
            //to handle item being clicked
        }
        binding.rvRecyclerView.adapter = homeAdapter
        binding.rvRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(),RecyclerView.VERTICAL))

        activityViewModel.attractionLiveData.observe(viewLifecycleOwner){ attractions ->
            homeAdapter.setData(attractions)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home_fragment, menu)
        /** Learn it
         * and implement it
         * someday
         */
//        val searchItem = menu.findItem(R.id.menuSearch)
//        val searchView = searchItem.actionView as SearchView
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                if(query!=null){
//                    activityViewModel.selectionAttractionLiveData
//                }
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return true
//            }
//
//        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuSearch -> {
                val attraction = activityViewModel.selectionAttractionLiveData.value ?: return true
                activityViewModel.locationSelectedLiveData.postValue(attraction)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}