package com.laaltentech.abou.vehicletap.flickrimagespack.owner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.kisannetwork.farmerdtm.util.DataBoundPagedListAdapter
import com.laaltentech.abou.vehicletap.GeneralUseClasses.AppExecutors
import com.laaltentech.abou.vehicletap.R
import com.laaltentech.abou.vehicletap.databinding.AdapterLayoutFlickrListBinding
import com.laaltentech.abou.vehicletap.parentsstudentfeature.data.FlickrImages

class FlickrImagesAdapter (private val dataBindingComponent: DataBindingComponent?,
                             appExecutors: AppExecutors,
                             private val callback:((FlickrImages, action:String)->Unit)?):
    DataBoundPagedListAdapter<FlickrImages, AdapterLayoutFlickrListBinding>(
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<FlickrImages>(){
            override fun areItemsTheSame(oldItem: FlickrImages, newItem: FlickrImages): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: FlickrImages, newItem: FlickrImages): Boolean {
                return oldItem.id == newItem.id
            }

        }
    ) {
    override fun retryAction() {
    }

    override fun createBinding(parent: ViewGroup): AdapterLayoutFlickrListBinding {
        val binding = DataBindingUtil
            .inflate<AdapterLayoutFlickrListBinding>(
                LayoutInflater.from(parent.context),
                R.layout.adapter_layout_flickr_list,
                parent,
                false,
                dataBindingComponent
            )
        return binding
    }



    override fun bind(binding: AdapterLayoutFlickrListBinding, item: FlickrImages, position: Int) {
        binding.flickerImages = item

        binding.rootViewAdapter.setOnClickListener {
            callback?.invoke(item, "tapOnRootView")
        }

        Glide.with(binding.root).load(item.url_o).into(binding.imageCentral)
    }
}