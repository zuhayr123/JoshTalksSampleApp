package com.laaltentech.abou.vehicletap.parentsstudentfeature.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.room.*

@Dao
interface FlickDAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFlickrImages(flickrListImages: List<FlickrImages>)

    @Query("SELECT MAX(indexInResponse) + 1 FROM FlickrImages")
    fun getNextIndex(): Int

    @Transaction
    @Query("SELECT * FROM FlickrImages ORDER BY indexInResponse")
    fun loadAll(): DataSource.Factory<Int, FlickrImages>

    @Query("SELECT * FROM FlickrImages ORDER BY indexInResponse")
    fun loadAllFromButtonClick(): LiveData<List<FlickrImages>>

    @Query("DELETE FROM FlickrImages")
    fun deleteFlickrImages()

    @Transaction
    fun insertDeleteMasterList(list: List<FlickrImages>) {
        val start = getNextIndex()
        val items = list.mapIndexed { index, child ->
            child.indexInResponse = start + index
            child
        }
        insertFlickrImages(items)
    }
}