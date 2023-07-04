package com.search.images.storage

import android.os.Parcelable
import javax.inject.Inject

class CacheStorage @Inject constructor() : Storage {

    private val cache = mutableMapOf<String, Any>()

    override fun <T> get(key: String): T? {
        return cache[key] as? T
    }

    override fun <T : Parcelable> put(key: String, value: T) {
        cache[key] = value
    }

    override fun remove(key: String) {
        cache.remove(key)
    }

    override fun clear() {
        cache.clear()
    }
}