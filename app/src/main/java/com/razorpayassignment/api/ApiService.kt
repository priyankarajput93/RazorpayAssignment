package com.razorpayassignment.api

import com.example.networkmodule.NetworkModule
import com.razorpayassignment.BuildConfig
import com.razorpayassignment.data.ApiResponse
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import timber.log.Timber
import java.util.concurrent.TimeUnit


private val BASE_URL = "https://demo.ezetap.com/mobileapps/"

interface ApiService {

    @GET("android_assignment.json")
    fun getData() : Call<ApiResponse>

    companion object {

        var apiService: ApiService? = null
        private fun createHttpClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
            client.readTimeout(1 * 60, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Timber.d(message) }
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(httpLoggingInterceptor)
            }
            return client.build()
        }
        fun getInstance(): ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(createHttpClient())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}