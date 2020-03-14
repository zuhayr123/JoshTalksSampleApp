package com.laaltentech.abou.vehicletap.flickrimagespack.observer

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.laaltentech.abou.vehicletap.GeneralUseClasses.AppExecutors
import com.laaltentech.abou.vehicletap.flickrimagespack.Repository.FlickrRepo
import com.laaltentech.abou.vehicletap.parentsstudentfeature.data.FlickrImages
import com.laaltentech.abou.vehicletap.util.paggingUtils.Listing
import javax.inject.Inject

class FlickrImagesViewModel @Inject constructor(
    private val repository: FlickrRepo,
    var executors: AppExecutors
) : ViewModel(), Observable {


    val apiCall = MutableLiveData<String>()

    val images = MutableLiveData<FlickrImages>()

    var limit = 0

    var tags = ""

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)    }

    fun notifyChange (){
        callbacks.notifyCallbacks(this,0,null)
    }


    var _query = MutableLiveData<String>()
    var liveData = MutableLiveData<String>()
    val query: LiveData<String>
        get() = _query

    val repoResult: LiveData<Listing<FlickrImages>> = Transformations.map(_query) {
        repository.tags = tags
        repository.fetchImageList()
    }
    val posts = Transformations.switchMap(repoResult) { it.pagedList }
    val networkState = Transformations.switchMap(repoResult) { it.networkState }
    val refreshState = Transformations.switchMap(repoResult) { it.refreshState }

    fun deleteFlickrImages(){
        repository.deleteFlickrImages()
    }


}