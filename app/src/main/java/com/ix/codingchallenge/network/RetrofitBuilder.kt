package com.ix.codingchallenge.network

import com.ix.codingchallenge.util.AppConstants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class for configuring retrofit for network calls.
 */
@Module
class RetrofitBuilder {

    // Creates a retrofit object and makes an API call
    @Provides
    fun getRetrofit(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}
