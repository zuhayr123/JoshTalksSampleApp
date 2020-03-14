package com.laaltentech.abou.vehicletap.flickrimagespack.owner.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.laaltentech.abou.vehicletap.R
import com.laaltentech.abou.vehicletap.databinding.FragmentFullSizeImageBinding
import com.laaltentech.abou.vehicletap.util.FragmentDataBindingComponent

class FullSizeImageFlickr: Fragment(){
    lateinit var binding: FragmentFullSizeImageBinding
    var dataBindingComponent = FragmentDataBindingComponent(this)

    var url = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_full_size_image, container, false, dataBindingComponent)

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        url = savedInstanceState?.getString("url")
            ?: FullSizeImageFlickrArgs.fromBundle(arguments!!).url

        Glide.with(binding.root).load(url).into(binding.mainImage)
    }

}