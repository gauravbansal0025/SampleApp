package com.sampleapp.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonFactory {
    val gson: Gson by lazy(LazyThreadSafetyMode.NONE) {
        buildGson()
    }

    fun buildGson(): Gson {
        return GsonBuilder()
            .addSerializationExclusionStrategy(GsonExclusionStrategy(GsonExcludeOutbound::class.java))
            .addDeserializationExclusionStrategy(GsonExclusionStrategy(GsonExcludeInbound::class.java))
            .create()
    }
}