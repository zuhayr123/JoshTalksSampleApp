package com.laaltentech.abou.vehicletap.GeneralUseClasses

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.http.*

interface WebService {

    @GET
    fun fetchImagesData(@Url url: String,
                        @Query("limit") limit: Int?,
                        @Query("tags") tags: String?): Call<CommonResponse>

    @GET
    fun fetchImagesDataFromButtonPress(@Url url: String,
                                       @Query("limit") limit: Int?,
                                       @Query("tags") tags: String?): LiveData<ApiResponse<CommonResponse>>


}