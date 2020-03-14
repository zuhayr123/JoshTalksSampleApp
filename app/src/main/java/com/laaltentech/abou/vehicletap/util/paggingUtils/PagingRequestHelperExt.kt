package com.kisannetwork.farmerdtm.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.laaltentech.abou.vehicletap.util.PagingRequestHelper
import com.laaltentech.abou.vehicletap.util.Status


fun PagingRequestHelper.createStatusLiveData(): LiveData<Status> {
    val liveData = MutableLiveData<Status>()
    addListener { report ->
        when {
            report.hasRunning() -> liveData.postValue(Status.LOADING)
            report.hasError() -> liveData.postValue(Status.ERROR)
            else -> liveData.postValue(Status.SUCCESS)
        }
    }
    return liveData
}
