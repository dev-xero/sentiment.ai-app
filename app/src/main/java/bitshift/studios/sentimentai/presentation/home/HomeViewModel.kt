/*
	* GNU GENERAL PUBLIC LICENSE
	* Copyright 2024 - DEV XERO

	* Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
	* Everyone is permitted to copy and distribute verbatim copies
	* of this license document, but changing it is not allowed.
*/
package bitshift.studios.sentimentai.presentation.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bitshift.studios.sentimentai.domain.model.Sentiment
import bitshift.studios.sentimentai.domain.network.SentimentRequest
import bitshift.studios.sentimentai.domain.network.SentimentUIState
import bitshift.studios.sentimentai.domain.repository.SentimentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "HOME"

// Home screen View Model
@HiltViewModel
class HomeViewModel @Inject constructor(
	private val sentimentRepo: SentimentRepo
): ViewModel() {
	private val _productName = mutableStateOf("")
	private val _productReview = mutableStateOf("")
	private val _isEnabled = mutableStateOf(true)
	private val _sentimentUIState = MutableStateFlow<SentimentUIState>(SentimentUIState.Loading)

	val productName: State<String> get() = _productName
	val productReview: State<String> get() = _productReview
	val isEnabled: State<Boolean> get() = _isEnabled
	val sentimentUIState: StateFlow<SentimentUIState> get() = _sentimentUIState

	// Update product name
	fun updateProductName(newName: String) {
		_productName.value = newName
	}

	// Update product review
	fun updateProductReview(newReview: String) {
		_productReview.value = newReview
	}

	// Analyze Sentiment
	fun analyzeSentiment() {
		Log.d(TAG, "product: ${productName.value}\nreview: ${productReview.value}")

		val reviewReq = SentimentRequest(review = _productReview.value)
		Log.d(TAG, reviewReq.toString())
		_isEnabled.value = false

		viewModelScope.launch {
			try {
				val res = sentimentRepo.analyzeSentiment(reviewReq)

				_sentimentUIState.value = if (res.isSuccessful) {
					res.body()?.let { SentimentUIState.Success(it) } ?: SentimentUIState.Error("Resource not found.")
				} else {
					SentimentUIState.Error("Failed to analyze sentiment.")
				}
			} catch (e: Exception) {
				SentimentUIState.Error("Couldn't reach the API. Try again later.")
			} finally {
				_isEnabled.value = true
			}
		}
	}
}