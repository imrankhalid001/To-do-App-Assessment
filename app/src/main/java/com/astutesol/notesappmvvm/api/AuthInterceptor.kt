package com.astutesol.notesappmvvm.api

import com.astutesol.notesappmvvm.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.addHeaderLenient
import javax.inject.Inject

class AuthInterceptor @Inject constructor( ) : Interceptor {

    @Inject
    lateinit var tokenManager: TokenManager

    // by the help of this class you will add bearer token in header of every request

    override fun intercept(chain: Interceptor.Chain): Response {
        val request =   chain.request().newBuilder()

        val token = tokenManager.getToken()
        request.addHeader("Authorization", "Bearer $token")


        return chain.proceed(request.build())
    }
}