package com.sampleapp.remote.moshiAdapter

import com.sampleapp.remote.Moshii
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Type

class MoshiMigrationConverter(private val moshiConverterFactory: MoshiConverterFactory)
    : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        for (annotation in annotations) {
            if (annotation.annotationClass == Moshii::class) {
                return moshiConverterFactory.responseBodyConverter(type, annotations, retrofit)
            }
        }
        return null
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        for (annotation in methodAnnotations) {
            if (annotation.annotationClass == Moshii::class) {
                return moshiConverterFactory.requestBodyConverter(
                    type,
                    parameterAnnotations,
                    methodAnnotations,
                    retrofit)
            }
        }
        return null
    }
}