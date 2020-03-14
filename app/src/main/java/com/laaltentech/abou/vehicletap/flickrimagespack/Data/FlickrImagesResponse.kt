package com.laaltentech.abou.vehicletap.flickrimagespack.Data

import com.google.gson.annotations.SerializedName
import com.laaltentech.abou.vehicletap.parentsstudentfeature.data.FlickrImages

class FlickrImagesResponse {
    @SerializedName("page")
    var page: String? = null

    @SerializedName("pages")
    var pages: String? = null

    @SerializedName("perpage")
    var perpage: String? = null

    @SerializedName("total")
    var total: String? = null

    @SerializedName("photo")
    var photo: List<FlickrImages>? = null
}