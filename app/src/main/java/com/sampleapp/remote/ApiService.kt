package com.sampleapp.remote

import com.sampleapp.model.PollutionData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService
{
    @Moshii
    @GET("environment/psi")
    fun getPollutionData(@Query("date_time") dateTime: String,@Query("date") date: String): Call<PollutionData>
}
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Moshii