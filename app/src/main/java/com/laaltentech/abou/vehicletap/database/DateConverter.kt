package com.laaltentech.abou.vehicletap.database

import androidx.databinding.ObservableField
import androidx.room.TypeConverter
import com.google.gson.Gson
import java.sql.Date

class DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toObserableDate(timestamp: Long?): ObservableField<Date?>? {
        return if (timestamp == null) null else {
            val date:  ObservableField<Date?>? = ObservableField()
            date?.set(Date(timestamp))
            return date
        }
    }

    @TypeConverter
    fun toObserableTimestamp(date: ObservableField<Date?>?): Long? {
        return date?.get()?.time
    }

    @TypeConverter
    fun listToJson(value: MutableList<String?>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String?): MutableList<String?>? {

        val objects = Gson().fromJson(value, Array<String?>::class.java)
        val list = objects?.toMutableList()
        return list
    }

}