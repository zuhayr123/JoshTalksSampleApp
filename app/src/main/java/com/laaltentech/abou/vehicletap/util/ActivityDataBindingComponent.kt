package com.laaltentech.abou.vehicletap.util

import android.app.Activity
import androidx.databinding.DataBindingComponent

class ActivityDataBindingComponent(activity: Activity) : DataBindingComponent {

    override fun getFragmentBindingAdapters(): FragmentBindingAdapters {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val adapter = ActivityBindingAdapters(activity)

    override fun getActivityBindingAdapters() = adapter
}

