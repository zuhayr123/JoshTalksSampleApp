package com.laaltentech.abou.vehicletap.flickrimagespack.owner.Activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.laaltentech.abou.vehicletap.R
import com.laaltentech.abou.vehicletap.databinding.ActivityFlickrImagesBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class FlickrImagesActivity : AppCompatActivity(), HasSupportFragmentInjector {
    lateinit var binding: ActivityFlickrImagesBinding

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_flickr_images)
    }
}