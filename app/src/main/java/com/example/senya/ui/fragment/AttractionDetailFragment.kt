package com.example.senya.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.navArgs
import com.example.senya.R
import com.example.senya.databinding.FragmentAttractionDetailBinding
import com.squareup.picasso.Picasso

class AttractionDetailFragment : BaseFragment() {

    private var _binding: FragmentAttractionDetailBinding? = null
    private val binding get() = _binding!!

    private val safeArgs: AttractionDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAttractionDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        activityViewModel.selectionAttractionLiveData.observe(viewLifecycleOwner) { attraction ->
            binding.tvTitle.text = attraction.title
            binding.tvDescription.text = attraction.description
            Picasso.get().load(attraction.image_urls[0]).into(binding.headerImageView);
            binding.monthsToVisitTextView.text = attraction.months_to_visit
            binding.numberOfFactsTextView.text = "${attraction.facts.size} facts"

            binding.numberOfFactsTextView.setOnClickListener {
                activityViewModel.factSelectedLiveData.postValue(attraction)
            }
        }
    }//FF018786

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_attraction_detail, menu)
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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}