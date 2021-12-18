package com.example.senya.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.senya.R
import com.example.senya.adapter.HomeFragmentController
import com.example.senya.data.Attraction
import com.example.senya.databinding.FragmentHomeBinding

private const val TAG = "HomeFragment"
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
        _binding = FragmentHomeBinding.inflate(inflater,container,false) //inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val epoxyController = HomeFragmentController{ attractionId ->
            activityViewModel.onAttractionSelected(attractionId)
            navController.navigate(R.id.action_homeFragment_to_attractionDetailFragment)
            //to handle item being clicked
        }
        binding.epoxyRecyclerView.setController(epoxyController)
//        binding.epoxyRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(),RecyclerView.VERTICAL))

        epoxyController.isLoading = true
        activityViewModel.attractionLiveData.observe(viewLifecycleOwner){ attractions ->
            epoxyController.attractions = attractions
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
//                return true
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
            R.id.menuItemLocation -> {
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