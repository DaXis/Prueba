package com.prueba.di.modules

import android.app.Application
import com.prueba.BuildConfig.DEBUG
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.prueba.common.base.BaseConfig
import com.prueba.common.base.SupportInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException
import kotlin.jvm.Throws

@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(
            if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        )
    }

    @Provides
    @Singleton
    fun getUnsafeOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = if (!BaseConfig.ENABLE_SSL_VALIDATION) {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<X509TrustManager>(object : X509TrustManager {

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }

                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                    //nothing to do
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                    //nothing to do
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance(PROTOCOL)
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            val hostnameVerifier = HostnameVerifier { _, _ -> true }
            OkHttpClient.Builder().sslSocketFactory(sslSocketFactory, trustAllCerts[0]).hostnameVerifier(hostnameVerifier)
        } else {
            OkHttpClient.Builder()
        }

        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(SupportInterceptor())
            .addInterceptor(interceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BaseConfig.BASE_URL)
            .build()
    }

    private companion object {
        const val CONNECT_TIMEOUT = 180L
        const val READ_TIMEOUT = 180L
        const val WRITE_TIMEOUT = 180L
        const val PROTOCOL = "SSL"
    }
}