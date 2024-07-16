/*
	* GNU GENERAL PUBLIC LICENSE
	* Copyright 2024 - DEV XERO

	* Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
	* Everyone is permitted to copy and distribute verbatim copies
	* of this license document, but changing it is not allowed.
*/
package bitshift.studios.sentimentai.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bitshift.studios.sentimentai.R
import bitshift.studios.sentimentai.presentation.theme.primaryContainerLight
import bitshift.studios.sentimentai.presentation.theme.primaryDark
import bitshift.studios.sentimentai.presentation.theme.surfaceDark
import bitshift.studios.sentimentai.presentation.theme.surfaceLight

// Home screen composable
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
	// STATE
	val isDarkTheme = isSystemInDarkTheme()

	// UI
	Scaffold(
		topBar = { HomeAppBar(isDarkTheme = isDarkTheme) },
		containerColor = if (isDarkTheme) Color(0xFF140C09) else surfaceLight
	) { paddingValues ->
		LazyColumn(
			modifier = modifier
				.fillMaxWidth()
				.padding(paddingValues)
		) {
			// CONTENT
		}
	}
}

// Home screen app bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeAppBar(
	modifier: Modifier = Modifier,
	isDarkTheme: Boolean
) {
	CenterAlignedTopAppBar(
		title = {
			Row(
				modifier = modifier.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.Center
			) {
				Image(
					modifier = Modifier.size(40.dp),
					painter = painterResource(id = R.drawable.appbar_icon),
					contentDescription = null
				)
				Spacer(modifier = Modifier.width(4.dp))
				Row {
					Text(
						text = stringResource(id = R.string.sentiment),
						color = if (isDarkTheme) primaryDark else Color(0xFF140C09),
						fontWeight = FontWeight.Bold,
						style = MaterialTheme.typography.headlineMedium
					)
					Text(
						text = ".AI",
						color = if (isDarkTheme) Color(0xFFE6BEAC) else Color(0xFFF97A32),
						fontWeight = FontWeight.Bold,
						style = MaterialTheme.typography.headlineMedium
					)
				}
			}
		},
		colors = TopAppBarDefaults.topAppBarColors(
			containerColor = if (isDarkTheme) Color(0xFF140C09) else surfaceLight
		)
	)
}