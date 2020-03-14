package com.laaltentech.abou.vehicletap.di.module
import androidx.paging.PagedList
import com.laaltentech.abou.vehicletap.flickrimagespack.Repository.FlickrRepo
import com.laaltentech.abou.vehicletap.GeneralUseClasses.AppExecutors
import com.laaltentech.abou.vehicletap.GeneralUseClasses.WebService
import com.laaltentech.abou.vehicletap.parentsstudentfeature.data.FlickDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideFlickrRepository(webservice: WebService, dao: FlickDAO, executor: AppExecutors, pagedListConfig: PagedList.Config): FlickrRepo {
        return FlickrRepo(webService = webservice, flickrDao = dao, appExecutors = executor, pagedListConfig = pagedListConfig)
    }

}