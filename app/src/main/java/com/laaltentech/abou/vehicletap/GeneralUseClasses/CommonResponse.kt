package com.laaltentech.abou.vehicletap.GeneralUseClasses

import com.google.gson.annotations.SerializedName
import com.laaltentech.abou.vehicletap.flickrimagespack.Data.FlickrImagesResponse

class CommonResponse {

    @field:SerializedName("photos")
    val photos: FlickrImagesResponse? = null

    @field:SerializedName("stat")
    val stat: String? = null
}