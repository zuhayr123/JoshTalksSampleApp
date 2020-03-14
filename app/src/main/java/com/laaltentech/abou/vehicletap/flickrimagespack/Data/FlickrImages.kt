package com.laaltentech.abou.vehicletap.parentsstudentfeature.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "FlickrImages")
class FlickrImages {
    @PrimaryKey(autoGenerate = true)
    var rid: Int = 0

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

    @SerializedName("ownername")
    var ownername: String? = null

    @SerializedName("tags")
    var tagsOthers: String? = null

    @SerializedName("url_m")
    var url_m: String? = null

    @SerializedName("url_t")
    var url_o: String? = null

    @SerializedName("datetaken")
    var datetaken: String? = null

    @SerializedName("originalformat")
    var originalformat: String? = null

    var tagsT : String = "-1"

    var indexInResponse : Int = 0
}