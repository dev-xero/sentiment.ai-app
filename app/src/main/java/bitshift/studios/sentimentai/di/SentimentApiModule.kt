/*
	* GNU GENERAL PUBLIC LICENSE
	* Copyright 2024 - DEV XERO

	* Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
	* Everyone is permitted to copy and distribute verbatim copies
	* of this license document, but changing it is not allowed.
*/
package bitshift.studios.sentimentai.di

import bitshift.studios.sentimentai.domain.network.Constants
import bitshift.studios.sentimentai.domain.network.SentimentAPI
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SentimentApiModule {
	@Provides
	@Singleton
	fun provideSentimentAPI(builder: Retrofit.Builder): SentimentAPI {
		return builder
			.build()
			.create(SentimentAPI::class.java)

	}

	@Provides
	@Singleton
	fun provideRetrofit(): Retrofit.Builder {
		return Retrofit.Builder()
			.baseUrl(Constants.BASE_URL)
			.addConverterFactory(MoshiConverterFactory.create())
	}
}