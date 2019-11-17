package com.sampleapp.remote

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

class GsonExclusionStrategy<T : Annotation> : ExclusionStrategy {

    private val clazz:Class<T>

    constructor(clazz: Class<T>) {
        this.clazz = clazz
    }


    override fun shouldSkipClass(clazz: Class<*>?): Boolean {
        return clazz?.getAnnotation(this.clazz)!=null
    }

    override fun shouldSkipField(f: FieldAttributes?): Boolean {
        return f?.getAnnotation(clazz)!=null
    }

}


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
annotation class GsonExcludeInbound

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
annotation class GsonExcludeOutbound