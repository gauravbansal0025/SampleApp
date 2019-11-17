package com.sampleapp.remote

import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sampleapp.remote.moshiAdapter.BigDecimalAdapter
import com.sampleapp.remote.moshiAdapter.MoshiMigrationConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    val API_URL = "https://api.data.gov.sg/v1/"

    var apiRestService: ApiService

    init {
        val client = getOkHttpClient()
        val moshi = Moshi.Builder()
            .add(BigDecimalAdapter)
            .add(KotlinJsonAdapterFactory())
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiMigrationConverter(MoshiConverterFactory.create(moshi)))
            .addConverterFactory(GsonConverterFactory.create(GsonFactory.gson))
            .build()
        apiRestService = retrofit.create(ApiService::class.java)
    }

    fun getApiService(): ApiService {
        return apiRestService
    }

    fun getOkHttpClient(): OkHttpClient {
        val okClientBuilder = OkHttpClient.Builder()
        okClientBuilder.addNetworkInterceptor(StethoInterceptor())
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        okClientBuilder.addInterceptor(httpLoggingInterceptor)
        /*okClientBuilder.addInterceptor { chain ->
            val builder = chain.request().newBuilder()
                .addHeader("Source", "mobile")
                .addHeader("Device-Name", App.get().getDeviceName())
                .addHeader("Brand-Name", Build.BRAND)
                .addHeader("IP", App.get().getHostAddress())
                .addHeader("Network-Provider",(App.get().getSystemService(
                    Context.TELEPHONY_SERVICE
                ) as TelephonyManager).networkOperatorName
                )


            chain.proceed(builder.build())
        }*/
        okClientBuilder.connectTimeout(300, TimeUnit.SECONDS)
        okClientBuilder.readTimeout(500, TimeUnit.SECONDS)
        okClientBuilder.writeTimeout(500, TimeUnit.SECONDS)
        okClientBuilder.retryOnConnectionFailure(true)

        return okClientBuilder.build()
    }


}