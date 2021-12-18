package com.example.senya.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyController
import com.example.senya.R
import com.example.senya.data.Attraction
import com.example.senya.databinding.ViewHolderAttractionBinding
import com.example.senya.ui.epoxy.LoadingEpoxyModel
import com.example.senya.ui.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso

class HomeFragmentController(private val onClickedCallBack: (String) -> Unit) :
    EpoxyController(){

    var isLoading: Boolean = false
        set(value) {
            field = value
            if (field) requestModelBuild()
        }


     var attractions = ArrayList<Attraction>()
         set(value) {
             field = value
             isLoading = false
             requestModelBuild()
         }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return AttractionViewHolder(parent)
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        (holder as AttractionViewHolder).onBind(attractions[position], onClickedCallBack)
//    }
//
//    override fun getItemCount(): Int {
//        return attractions.size
//    }
//
//    fun setData(attractions: List<Attraction>) {
//        this.attractions.clear()
//        this.attractions.addAll(attractions)
//        notifyDataSetChanged()
//    }

    override fun buildModels() {
        if(isLoading){
             LoadingEpoxyModel().id("Loading_state").addTo(this)
            return
        }

        if(attractions.isEmpty()){
            // todo
            return
        }

        attractions.forEach { attraction ->
            AttractionEpoxyModel(attraction, onClickedCallBack)
                .id(attraction.id)
                .addTo(this)
        }
    }

    //
//    inner class AttractionViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
//        LayoutInflater.from(parent.context)
//            .inflate(R.layout.view_holder_attraction, parent, false)
//    ) {
//        private val binding = ViewHolderAttractionBinding.bind(itemView)
//        fun onBind(attraction: Attraction, onClicked: (String) -> Unit) {
//            binding.titleTextView.text = attraction.title
//            Picasso.get().load(attraction.image_urls[0]).into(binding.headerImageView);
//            binding.monthsToVisitTextView.text = attraction.months_to_visit
//
//            binding.root.setOnClickListener {
//                onClicked(attraction.id)
//            }
//        }
//    }

    data class AttractionEpoxyModel(
        val attraction: Attraction,
        val onClicked: (String) -> Unit
    ) : ViewBindingKotlinModel<ViewHolderAttractionBinding>(
        R.layout.view_holder_attraction
    ) {
        override fun ViewHolderAttractionBinding.bind() {
            titleTextView.text = attraction.title
            Picasso.get().load(attraction.image_urls[0]).into(headerImageView);
            monthsToVisitTextView.text = attraction.months_to_visit

            root.setOnClickListener {
                onClicked(attraction.id)
        }

    }

} }