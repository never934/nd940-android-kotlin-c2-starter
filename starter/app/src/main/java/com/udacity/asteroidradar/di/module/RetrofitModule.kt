package com.udacity.asteroidradar.di.module

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Constants.CONNECT_TIMEOUT_MILLIS
import com.udacity.asteroidradar.Constants.CONTENT_TYPE
import com.udacity.asteroidradar.Constants.READ_TIMEOUT_MILLIS
import com.udacity.asteroidradar.di.EnvScope

import java.util.concurrent.TimeUnit

import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
class RetrofitModule {

    @Provides
    @EnvScope
    internal fun provideRetrofit(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl(Constants.BASE_URL).build()
    }

    @Provides
    @EnvScope
    internal fun provideRetrofitBuilder(client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
    }

    @Provides
    @EnvScope
    internal fun provideOkHttpClient(
        sslSocketFactory: SSLSocketFactory,
        trustManager: X509TrustManager,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(CONNECT_TIMEOUT_MILLIS.toLong(), TimeUnit.MINUTES)
        httpClientBuilder.readTimeout(READ_TIMEOUT_MILLIS.toLong(), TimeUnit.MINUTES)
        httpClientBuilder.sslSocketFactory(sslSocketFactory, trustManager)


        httpClientBuilder.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .addHeader(CONTENT_TYPE, "application/json")
                .build()
            chain.proceed(request)
        }
        httpClientBuilder.addInterceptor(loggingInterceptor)
        return httpClientBuilder.build()
    }
}