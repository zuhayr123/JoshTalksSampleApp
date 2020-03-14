package com.laaltentech.abou.vehicletap.flickrimagespack.Data

import com.google.gson.annotations.SerializedName

class IndividualImageResponse {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("owner")
    var owner: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("secret")
    var secret: String? = null

    @SerializedName("ispublic")
    var ispublic: String? = null

    @SerializedName("isfamily")
    var isfamily: String? = null

    @SerializedName("isfriend")
    var isfriend: String? = null
}