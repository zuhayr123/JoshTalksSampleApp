package com.laaltentech.abou.vehicletap.di.module
import com.laaltentech.abou.vehicletap.flickrimagespack.owner.Fragment.FlickrMainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeFlickrMainFragment(): FlickrMainFragment
}