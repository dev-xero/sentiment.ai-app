/*
	* GNU GENERAL PUBLIC LICENSE
	* Copyright 2024 - DEV XERO

	* Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
	* Everyone is permitted to copy and distribute verbatim copies
	* of this license document, but changing it is not allowed.
*/
package bitshift.studios.sentimentai.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Sentiment Response Object
@JsonClass(generateAdapter = true)
data class Sentiment(
    @Json(name = "code")
    val code: Int,
    @Json(name = "message")
    val message: String,
    @Json(name = "payload")
    val payload: String,
    @Json(name = "success")
    val success: Boolean
)