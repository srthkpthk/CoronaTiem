package com.srthk.coronatiem.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.srthk.coronatiem.util.InternetNotAvailableException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context) : Interceptor {
    private val appCon = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (internetNotAvailable())
            throw InternetNotAvailableException("Internet is not available,Check for any errors")
        return chain.proceed(chain.request())
    }

    private fun internetNotAvailable(): Boolean {
        var result = false
        val connectivityManager =
            appCon.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return result
    }
}
