package com.laaltentech.abou.vehicletap.flickrimagespack.Repository

import android.util.Log
import com.kisannetwork.farmerdtm.util.DataBoundBoundaryCallback
import com.laaltentech.abou.vehicletap.GeneralUseClasses.AppExecutors
import com.laaltentech.abou.vehicletap.GeneralUseClasses.CommonResponse
import com.laaltentech.abou.vehicletap.GeneralUseClasses.URL_HUB
import com.laaltentech.abou.vehicletap.GeneralUseClasses.WebService
import com.laaltentech.abou.vehicletap.parentsstudentfeature.data.FlickrImages
import retrofit2.Call

class ImageFlickrDataBoundaryCallback (
    private val handleResponse: (CommonResponse?) -> Unit,
    appExecutors: AppExecutors,
    private val webService: WebService,
    private val tags : String
    ) : DataBoundBoundaryCallback<FlickrImages, CommonResponse>(appExecutors = appExecutors) {
    override fun zeroItemLoaded(): Call<CommonResponse> {
        Log.e("TAG", "Item at start was loaded")
        return webService.fetchImagesData(
            url= URL_HUB.FETCH_IMAGES,
            limit = 1,
            tags = tags
        )
    }

    override fun itemAtEndLoaded(item: FlickrImages): Call<CommonResponse> {
        return webService.fetchImagesData(
            url = URL_HUB.FETCH_IMAGES,
            limit = (((item.indexInResponse+1)/10)+1),
            tags = tags
        )
    }

    override fun handleAPIResponse(response: CommonResponse?) {
        handleResponse(response)
    }

}