package com.bangkit.vegalicious.data.remote.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
	
	companion object {
		private var auth: String? = null // null
		
		fun getApiService(isModel: Boolean = false): ApiService {
			var url = "https://vegalicious-dot-vegalicious-2.et.r.appspot.com/"
//			if(isModel) url = "https://recommendation-system-endpoint-yulxuobada-et.a.run.app/"
			
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
				.baseUrl(url)
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