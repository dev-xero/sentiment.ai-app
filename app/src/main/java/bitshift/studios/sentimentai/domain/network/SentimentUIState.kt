/*
	* GNU GENERAL PUBLIC LICENSE
	* Copyright 2024 - DEV XERO

	* Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
	* Everyone is permitted to copy and distribute verbatim copies
	* of this license document, but changing it is not allowed.
*/
package bitshift.studios.sentimentai.domain.network

import bitshift.studios.sentimentai.domain.model.Sentiment

// Sentiment UI State
sealed class SentimentUIState {
	data object Loading: SentimentUIState()
	data class Success(val res: Sentiment): SentimentUIState()
	data class Error(val headline: String): SentimentUIState()
}