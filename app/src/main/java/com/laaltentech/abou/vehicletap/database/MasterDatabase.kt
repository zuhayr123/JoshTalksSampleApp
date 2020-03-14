package com.laaltentech.abou.vehicletap.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.laaltentech.abou.vehicletap.parentsstudentfeature.data.FlickDAO
import com.laaltentech.abou.vehicletap.parentsstudentfeature.data.FlickrImages

@Database(entities =
[
(FlickrImages::class)], version = 12, exportSchema = false)

@TypeConverters(DateConverter::class)

abstract class MasterDatabase : RoomDatabase() {

    abstract fun flickDAO(): FlickDAO
}