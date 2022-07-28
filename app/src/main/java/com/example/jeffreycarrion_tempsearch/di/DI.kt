package com.example.jeffreycarrion_tempsearch.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.jeffreycarrion_tempsearch.api.TempService
import com.example.jeffreycarrion_tempsearch.api.WeatherRepoImpl
import com.example.jeffreycarrion_tempsearch.viewmodel.WeatherViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DI {
    private val service = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideHttpClient())
        .build()
        .create(TempService::class.java)

    private fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY

            })
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun provideRepository() = WeatherRepoImpl(service)
    private fun provideDispatcher()= Dispatchers.IO


    fun providerViewModel(storeOwner: ViewModelStoreOwner):WeatherViewModel{
        return ViewModelProvider(storeOwner, object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return WeatherViewModel(provideRepository(), provideDispatcher()) as T
            }
        })[WeatherViewModel::class.java]

    }
}