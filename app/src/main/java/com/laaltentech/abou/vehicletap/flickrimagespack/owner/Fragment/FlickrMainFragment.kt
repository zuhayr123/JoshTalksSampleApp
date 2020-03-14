package com.laaltentech.abou.vehicletap.flickrimagespack.owner.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.bind
import androidx.databinding.DataBindingUtil.getDefaultComponent
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.gms.common.internal.service.Common
import com.laaltentech.abou.vehicletap.flickrimagespack.observer.FlickrImagesViewModel
import com.laaltentech.abou.vehicletap.flickrimagespack.owner.adapter.FlickrImagesAdapter
import com.laaltentech.abou.vehicletap.GeneralUseClasses.AppExecutors
import com.laaltentech.abou.vehicletap.LoginPage.Owner.Fragments.LoginOrSignupChooseDirections
import com.laaltentech.abou.vehicletap.R
import com.laaltentech.abou.vehicletap.databinding.FragmentFlickrImagesBinding
import com.laaltentech.abou.vehicletap.di.Injectable
import com.laaltentech.abou.vehicletap.util.Commons
import com.laaltentech.abou.vehicletap.util.FragmentDataBindingComponent
import com.laaltentech.abou.vehicletap.util.Status
import javax.inject.Inject

class FlickrMainFragment : Fragment() , Injectable {

    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: FragmentFlickrImagesBinding

    lateinit var adapter : FlickrImagesAdapter

    var dataBindingComponent = FragmentDataBindingComponent(this)

    private val detailsViewModel: FlickrImagesViewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory)
            .get(FlickrImagesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flickr_images, container, false, dataBindingComponent)

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        detailsViewModel.deleteFlickrImages()

        binding.progress.progressBar.visibility = View.GONE

        adapter = FlickrImagesAdapter(
            dataBindingComponent = getDefaultComponent(),
            appExecutors = appExecutors) {item, tapAction ->
            when(tapAction){
                "tapOnRootView" -> {
                    if(Commons.isNetworkAvailable(activity)) {
                        val action =
                            FlickrMainFragmentDirections.actionFlickrImagesToFlickrImagesFull()
                        action.url = item.url_m!!
                        findNavController().navigate(action)
                    }
                    else{
                        Toast.makeText(requireContext(), "Oops!! Sorry No Network Available", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.studentDetailsRecyclerView.adapter = adapter

        detailsViewModel.let { it ->
            it.posts.observe(viewLifecycleOwner, Observer { list ->
                Log.e("Tag,", "this was duly called 1 ${list.size}")
                adapter.submitList(list)
                if(list.size == 0){
                    binding.emptyView.visibility = View.VISIBLE
                }
                else{
                    binding.emptyView.visibility = View.GONE

                }
                detailsViewModel.limit = (list.size/10)+1
                detailsViewModel.notifyChange()
                adapter.notifyDataSetChanged()

                list.forEach{
                    if(it != null) {
                        it.tagsT = detailsViewModel.tags
                    }
                }
            })

            it.networkState.observe(viewLifecycleOwner, Observer { status ->
                status?.let {
                    Log.e("Tag,", "this was duly called 2")

                    when(status){
                        Status.LOADING -> {
                            binding.progress.progressBar.visibility = View.VISIBLE
//                            Toast.makeText(requireContext(), "Please Wait Images Loading", Toast.LENGTH_SHORT).show()
                        }

                        Status.ERROR -> {
                            binding.progress.progressBar.visibility = View.GONE
                            Toast.makeText(requireContext(), "Oops!! Something went wrong", Toast.LENGTH_SHORT).show()
                        }

                        Status.SUCCESS -> {
                            binding.progress.progressBar.visibility = View.GONE
                        }
                        else -> {
                            binding.progress.progressBar.visibility = View.GONE
                        }
                    }

                }
            })

            it.refreshState.observe(viewLifecycleOwner, Observer { status ->
                Log.e("Tag,", "this was duly called 3")

            })
        }

        Log.e("tag", "from the fragment as expected")

        binding.submitTag.setOnClickListener{
            detailsViewModel.deleteFlickrImages()
            detailsViewModel.tags = binding.tags.text.toString()
            detailsViewModel._query.postValue("trigger")

        }
    }
}