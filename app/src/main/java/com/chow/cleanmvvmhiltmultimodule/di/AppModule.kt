package com.chow.cleanmvvmhiltmultimodule.di

import android.app.Application
import androidx.room.Room
import com.chow.cleanmvvmhiltmultimodule.BuildConfig
import com.chow.cleanmvvmhiltmultimodule.data.local.db.UserDatabase
import com.chow.cleanmvvmhiltmultimodule.data.remote.api.ApiService
import com.chow.cleanmvvmhiltmultimodule.data.remote.api.AuthInterceptor
import com.chow.cleanmvvmhiltmultimodule.data.remote.api.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(
        app: Application,
        callback: UserDatabase.Callback
    ) = Room.databaseBuilder(app, UserDatabase::class.java, "user_database")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideTaskDao(db: UserDatabase) = db.userDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl("")
        .build()

    @Provides
    @Singleton
    fun provideAuthInterceptor() = AuthInterceptor()

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = LoggingInterceptor()

    @Provides
    @Singleton
    fun provideHttpClient(authInterceptor: AuthInterceptor, loggingInterceptor: LoggingInterceptor) = OkHttpClient.Builder().run {
        addInterceptor(authInterceptor)
        if (BuildConfig.DEBUG) {
            addInterceptor(loggingInterceptor)
        }
        build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope