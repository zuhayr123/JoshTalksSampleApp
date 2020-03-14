package com.laaltentech.abou.vehicletap.util
import com.laaltentech.abou.vehicletap.GeneralUseClasses.WebService

class WebServiceHolder {
    private var webservice: WebService? = null

    fun apiService(): WebService? {
        return this.webservice
    }

    fun setAPIService(webservice: WebService) {
        this.webservice = webservice
    }

    companion object {
        internal var webServiceHolder: WebServiceHolder? = null

        val instance: WebServiceHolder
            get() {
                if (webServiceHolder == null) {
                    webServiceHolder = WebServiceHolder()
                }
                return webServiceHolder!!
            }
    }
}