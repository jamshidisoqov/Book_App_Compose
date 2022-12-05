package uz.gita.book_app_compose.di

import android.content.Context
import android.content.SharedPreferences
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.book_app_compose.data.local.prefs.MySharedPref
import uz.gita.book_app_compose.data.remote.api.BookApi
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

// Created by Jamshid Isoqov on 12/5/2022
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val BASE_URL = "http://143.198.48.222:82/"

    private const val CONNECTION_TIME_OUT = 5000L


    @[Provides Singleton]
    fun provideGson(): Gson = GsonBuilder().create()

    @[Provides Singleton]
    fun provideClient(
        @ApplicationContext context: Context,
        mySharedPref: MySharedPref
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor.Builder(context).build())
            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.MILLISECONDS)
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                if (mySharedPref.token.isNotEmpty())
                    requestBuilder.addHeader("Authorization", "Bearer ${mySharedPref.token}")
                chain.proceed(requestBuilder.build())
            }
            .readTimeout(CONNECTION_TIME_OUT, TimeUnit.MILLISECONDS)
            .build()

    @[Provides Singleton Named(value = "mainApi")]
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @[Provides Singleton]
    fun provideBookApi(@Named("mainApi") retrofit: Retrofit): BookApi =
        retrofit.create(BookApi::class.java)

    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("app_data", Context.MODE_PRIVATE)

    @[Provides Singleton]
    fun provideSharedPrefs(
        @ApplicationContext context: Context,
        sharedPreferences: SharedPreferences
    ): MySharedPref =
        MySharedPref(context, sharedPreferences)

}