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
import bitshift.studios.sentimentai.domain.repository.SentimentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// Home screen View Model
@HiltViewModel
class HomeViewModel @Inject constructor(
	private val sentimentRepo: SentimentRepo
): ViewModel() {
	private val _productName = mutableStateOf("")
	private val _productReview = mutableStateOf("")
	private val _isEnabled = mutableStateOf(true)
	private val _sentiment: MutableState<Sentiment>? = null
	val productName: State<String> get() = _productName
	val productReview: State<String> get() = _productReview
	val sentiment: MutableState<Sentiment>? get() = _sentiment
	val isEnabled: State<Boolean> get() = _isEnabled

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
		Log.d("HOME", "product: ${productName.value}\nreview: ${productReview.value}")
		val reviewReq = SentimentRequest(review = _productReview.value)
		_isEnabled.value = false
		viewModelScope.launch {
			val res = sentimentRepo.analyzeSentiment(reviewReq)
			_sentiment?.value = res
			_isEnabled.value = true
			Log.d("HOME", res.toString())

		}
	}
}