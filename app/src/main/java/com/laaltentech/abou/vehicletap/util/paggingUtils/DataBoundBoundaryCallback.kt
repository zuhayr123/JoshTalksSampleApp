package com.kisannetwork.farmerdtm.util

import android.util.Log
import androidx.annotation.MainThread
import androidx.paging.PagedList
import com.google.gson.Gson
import com.laaltentech.abou.vehicletap.GeneralUseClasses.AppExecutors
import com.laaltentech.abou.vehicletap.util.PagingRequestHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class DataBoundBoundaryCallback<T, R> (
        private val appExecutors: AppExecutors
) : PagedList.BoundaryCallback<T>() {

    val helper = PagingRequestHelper(appExecutors.diskIO())
    val networkState = helper.createStatusLiveData()

    @MainThread
    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
//            Logger.e(Thread.currentThread(), "onZeroItemsLoaded call")
            zeroItemLoaded().enqueue(createWebserviceCallback(it))
        }
    }

    /**
     * User reached to the end of the list.
     */
    @MainThread
    override fun onItemAtEndLoaded(item: T) {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
//            Log.e("APi_Ka_Response", "item at end loaded ${Gson().toJson(item)}")//todo remove gson

//            Logger.e(Thread.currentThread(), "onItemAtEndLoaded call")
            itemAtEndLoaded(item).enqueue(createWebserviceCallback(it))
        }
    }

    protected abstract fun zeroItemLoaded(): Call<R>

    protected abstract fun itemAtEndLoaded(item: T): Call<R>

    protected abstract fun handleAPIResponse(response: R?)

    private fun createWebserviceCallback(it: PagingRequestHelper.Request.Callback)
            : Callback<R> {
//        Logger.e(Thread.currentThread(), "createWebserviceCallback call")
        return object : Callback<R> {
            override fun onFailure(
                    call: Call<R>,
                    t: Throwable) {
//                Log.e("APi_Ka_Response failure", " ${Gson().toJson(t)}")//todo remove gson
                it.recordFailure(t)
            }

            override fun onResponse(
                    call: Call<R>,
                    response: Response<R>) {

                appExecutors.diskIO().execute {

                    handleAPIResponse(response.body())
                    it.recordSuccess()
                }
            }
        }
    }

}