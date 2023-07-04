package com.search.images.storage

import android.os.Parcelable

interface Storage {

    fun <T> get(key: String): T?
    fun <T : Parcelable> put(key: String, value: T)
    fun remove(key: String)
    fun clear()
}
