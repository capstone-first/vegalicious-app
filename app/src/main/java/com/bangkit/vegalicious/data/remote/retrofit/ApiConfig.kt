package com.bangkit.vegalicious.data.remote.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
	
	companion object {
		private var auth: String? = null
		
		fun getApiService(): ApiService {
			
			val loggingInterceptor =
				HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
			
			val authInterceptor = Interceptor { chain ->
				val req = chain.request()
				val requestHeaders = req.newBuilder()
					.addHeader("Authorization", "Bearer $auth")
					.build()
				chain.proceed(requestHeaders)
			}
			
			val client = OkHttpClient.Builder()
				.addInterceptor(loggingInterceptor)
				.addInterceptor(authInterceptor)
				.build()
			
			val retrofit = Retrofit.Builder()
				.baseUrl("https://vegalicious-2.et.r.appspot.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.client(client)
				.build()
			
			return retrofit.create(ApiService::class.java)
		}
		
		fun setAuth(auth: String) {
			Companion.auth = auth
		}
		
		fun clearAuth() {
			auth = null
		}
	}
}