package com.laaltentech.abou.vehicletap.di.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.laaltentech.abou.vehicletap.flickrimagespack.observer.FlickrImagesViewModel
import com.laaltentech.abou.vehicletap.di.ViewModelKey
import com.laaltentech.abou.vehicletap.factory.AppModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FlickrImagesViewModel::class)
    abstract fun bindFlickrImagesViewModel(flickrImagesViewModel: FlickrImagesViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppModelFactory): ViewModelProvider.Factory

}
