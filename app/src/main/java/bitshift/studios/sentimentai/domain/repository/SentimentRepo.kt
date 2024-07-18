/*
	* GNU GENERAL PUBLIC LICENSE
	* Copyright 2024 - DEV XERO

	* Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
	* Everyone is permitted to copy and distribute verbatim copies
	* of this license document, but changing it is not allowed.
*/
package bitshift.studios.sentimentai.domain.repository

import bitshift.studios.sentimentai.domain.model.Sentiment
import bitshift.studios.sentimentai.domain.network.SentimentRequest
import bitshift.studios.sentimentai.domain.network.SentimentAPI
import retrofit2.Response
import javax.inject.Inject


// Sentiment Repo
class SentimentRepo @Inject constructor(
	private val sentimentAPI: SentimentAPI
) {
	// Make sentiment analysis requests
	suspend fun analyzeSentiment(req: SentimentRequest): Response<Sentiment> {
		return sentimentAPI.analyze(req)
	}
}