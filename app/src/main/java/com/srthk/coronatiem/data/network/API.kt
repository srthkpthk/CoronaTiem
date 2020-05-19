package com.srthk.coronatiem.data.network

import com.srthk.coronatiem.data.db.entries.National
import com.srthk.coronatiem.util.BASE_URL
import com.srthk.coronatiem.util.NATIONAL_DATA
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//https://api.covid19india.org/data.json
interface API {
    @GET(NATIONAL_DATA)
    suspend fun getNationalData(): Response<National>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): API {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(API::class.java)
        }
    }
}