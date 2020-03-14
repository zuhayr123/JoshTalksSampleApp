package com.laaltentech.abou.vehicletap.di.module
import com.laaltentech.abou.vehicletap.flickrimagespack.owner.Activity.FlickrImagesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [(FragmentModule::class)])
    abstract fun contributeFlickrActivity(): FlickrImagesActivity
}