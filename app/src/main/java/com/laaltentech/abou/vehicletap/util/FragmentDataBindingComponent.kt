package com.laaltentech.abou.vehicletap.util

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment

/**
 * A Data Binding Component implementation for fragments.
 */
class FragmentDataBindingComponent(var fragment: Fragment) : DataBindingComponent {

    override fun getActivityBindingAdapters() = ActivityBindingAdapters(fragment.requireActivity())

    private val adapter = FragmentBindingAdapters(fragment)

    override fun getFragmentBindingAdapters() = adapter

}
