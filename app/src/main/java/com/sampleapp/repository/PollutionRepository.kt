package com.sampleapp.repository

import com.sampleapp.model.PollutionData
import com.sampleapp.remote.RetrofitFactory
import retrofit2.Response

class PollutionRepository ()
{
    val remoteDataSource=RemoteDataSource()
    suspend fun getPollutionData( dateTime:String,time:String): Response<PollutionData>
    {
        val response= remoteDataSource.getPollutionData(dateTime,time)
        return response
    }
}