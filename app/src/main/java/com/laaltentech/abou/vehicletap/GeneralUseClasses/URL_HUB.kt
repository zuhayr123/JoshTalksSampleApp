package com.laaltentech.abou.vehicletap.GeneralUseClasses

class URL_HUB{
    companion object{
        val BASE_URL = "http://3.15.174.179/fleet/user_details/"
        val POST_USER_DETAILS = BASE_URL +"add_user_details.php"
        val FETCH_IMAGES = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=dd4a16666bdf3c2180b43bec8dd1534a&format=json&nojsoncallback=1&per_page=10&extras=description, license, date_upload, date_taken, owner_name, icon_server, original_format, last_update, geo, tags, machine_tags, o_dims, views, media, path_alias, url_sq, url_t, url_s, url_q, url_m, url_n, url_z, url_c, url_l, url_o"
    }

}