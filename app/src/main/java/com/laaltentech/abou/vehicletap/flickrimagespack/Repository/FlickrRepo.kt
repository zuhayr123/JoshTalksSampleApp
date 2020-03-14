package com.laaltentech.abou.vehicletap.flickrimagespack.Repository

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.kisannetwork.farmerdtm.util.DataBoundBoundaryCallback
import com.kisannetwork.farmerdtm.util.PaginationRepository
import com.kisannetwork.outletsurvey.util.remoteUtils.NetworkBoundResource
import com.laaltentech.abou.vehicletap.GeneralUseClasses.*
import com.laaltentech.abou.vehicletap.parentsstudentfeature.data.FlickDAO
import com.laaltentech.abou.vehicletap.parentsstudentfeature.data.FlickrImages
import com.laaltentech.abou.vehicletap.util.paggingUtils.Listing
import retrofit2.Call
import javax.inject.Inject

class FlickrRepo @Inject constructor(
    private val webService: WebService,
    private val appExecutors: AppExecutors,
    private val flickrDao: FlickDAO,
    val pagedListConfig: PagedList.Config
) : PaginationRepository<FlickrImages, CommonResponse>(
    executors = appExecutors,
    pagedListConfig = pagedListConfig
) {
    var tags = ""

    override fun refreshAPI(): Call<CommonResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun boundaryCallback(): DataBoundBoundaryCallback<FlickrImages, CommonResponse> {
        return ImageFlickrDataBoundaryCallback(
            handleResponse = this::insertResultIntoDb,
            appExecutors = appExecutors,
            webService = webService,
            tags = tags
        )
    }

    override fun dataSourceFactory(): DataSource.Factory<Int, FlickrImages> {
        return flickrDao.loadAll()
    }

    override fun refreshOperation(response: CommonResponse?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun insertResultIntoDb(body: CommonResponse?) {

//        Logger.e(Thread.currentThread(),"response: ${Gson().toJson(body)}")
        if (body?.stat.equals("ok")) {
            flickrDao.insertDeleteMasterList(list = body?.photos?.photo!!)
        }
    }

    @MainThread
    fun fetchImageList(): Listing<FlickrImages> {
        return response()
    }

    fun deleteFlickrImages() {
        appExecutors.diskIO().execute {
            flickrDao.deleteFlickrImages()
        }
    }
}