package com.sampleapp.repository

import com.sampleapp.model.PollutionData
import com.sampleapp.remote.RetrofitFactory
import retrofit2.Response

class RemoteDataSource ()
{
    suspend fun getPollutionData( dateTime:String,time:String):Response<PollutionData>
    {
        val response=RetrofitFactory.getApiService().getPollutionData(dateTime,time).execute()
        return response
    }
}