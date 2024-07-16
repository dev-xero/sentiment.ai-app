/*
	* GNU GENERAL PUBLIC LICENSE
	* Copyright 2024 - DEV XERO

	* Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
	* Everyone is permitted to copy and distribute verbatim copies
	* of this license document, but changing it is not allowed.
*/
package bitshift.studios.sentimentai.presentation.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

// Home screen View Model
class HomeViewModel: ViewModel() {
	private val _productName = mutableStateOf("")
	private val _productReview = mutableStateOf("")
	val productName: State<String> get() = _productName
	val productReview: State<String> get() = _productReview

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
	}
}