package com.laaltentech.abou.vehicletap.util

/**
 * Status of a resource that is provided to the UI.
 *
 * These are usually created by the Repository classes where they return
 * `LiveData<Resource<T>>` to pass back the latest data to the UI with its fetch status.
 */

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    SERVER_ERROR,
    MISSING_PARAMETER,
    DUPLICATE_ENTRY
}

enum class UploadStatus{
    NEWENTRY,
    UNSYNCDATA,
    UPLOADED
}
