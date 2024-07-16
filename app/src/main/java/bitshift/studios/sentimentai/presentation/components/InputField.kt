/*
	* GNU GENERAL PUBLIC LICENSE
	* Copyright 2024 - DEV XERO

	* Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
	* Everyone is permitted to copy and distribute verbatim copies
	* of this license document, but changing it is not allowed.
*/
package bitshift.studios.sentimentai.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import bitshift.studios.sentimentai.utils.conditional

// Input Field Composable
@Composable
fun InputField(
	modifier: Modifier = Modifier,
	isTextArea: Boolean = false,
	placeholder: String,
	isDarkTheme: Boolean,
	focusManager: FocusManager,
	text: String,
	onChange: (value: String) -> Unit,
) {
	// UI
	OutlinedTextField(
		modifier = modifier
			.fillMaxWidth()
			.conditional(isTextArea, {
				height(160.dp)
			})
		,
		value = text,
		onValueChange = { onChange(it) },
		placeholder = { Text(text = placeholder) },
		singleLine = !isTextArea,
		maxLines = if (isTextArea) 24 else 1,
		shape = RoundedCornerShape(12.dp),
		colors = OutlinedTextFieldDefaults.colors(
			unfocusedBorderColor = if (isDarkTheme) Color(0xFF3D332E) else Color(0xFFE6BEAC),
			focusedBorderColor = Color(0xFFF97A32),
			unfocusedContainerColor = if (isDarkTheme) Color(0xFF271E1A) else Color(0xFFFFF1EB),
			focusedContainerColor = if (isDarkTheme) Color(0xFF271E1A) else Color(0xFFFFF1EB),
			focusedTextColor = if (isDarkTheme) Color(0xFFE6BEAC) else Color(0xFF765749),
			unfocusedTextColor = if (isDarkTheme) Color(0xFFE6BEAC) else Color(0xFF765749),
		),
		keyboardActions = KeyboardActions(onDone = {
			focusManager.clearFocus()
		})
	)
}