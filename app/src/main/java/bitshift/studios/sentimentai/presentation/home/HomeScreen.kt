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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import bitshift.studios.sentimentai.R
import bitshift.studios.sentimentai.domain.network.SentimentUIState
import bitshift.studios.sentimentai.presentation.components.InputField
import bitshift.studios.sentimentai.presentation.theme.onPrimaryContainerLight
import bitshift.studios.sentimentai.presentation.theme.primaryDark
import bitshift.studios.sentimentai.presentation.theme.surfaceLight
import kotlinx.coroutines.launch

private suspend fun clickedAnalyze(
	focusManager: FocusManager,
	viewModel: HomeViewModel
) {
	focusManager.clearFocus()
	viewModel.analyzeSentiment()
}

// Home screen composable
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
	// STATE
	val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
	val sentimentUIState = homeViewModel.sentimentUIState.collectAsState()
	val isDarkTheme = isSystemInDarkTheme()
	val focusManager = LocalFocusManager.current
	val snackbarHostState = remember { SnackbarHostState() }
	val snackbarMessage = homeViewModel.snackbarMessage.value
	val scope = rememberCoroutineScope()

	// Show Snackbar when there's a message
	LaunchedEffect(snackbarMessage) {
		snackbarMessage?.let {
			snackbarHostState.showSnackbar(it)
			homeViewModel.clearSnackbarMessage()
		}
	}

	// UI
	Scaffold(
		topBar = { HomeAppBar(isDarkTheme = isDarkTheme) },
		snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
		containerColor = if (isDarkTheme) Color(0xFF140C09) else surfaceLight
	) { paddingValues ->
		LazyColumn(
			modifier = modifier
				.fillMaxWidth()
				.padding(paddingValues)
				.padding(horizontal = 12.dp)
		) {
			// Intro section
			item {

				InputField(
					placeholder = stringResource(id = R.string.product),
					isDarkTheme = isDarkTheme,
					focusManager = focusManager,
					text = homeViewModel.productName.value,
					onChange = { homeViewModel.updateProductName(it) }
				)
					
				Spacer(modifier = Modifier.height(16.dp))
				
				// Review Field
				InputField(
					placeholder = stringResource(id = R.string.your_review),
					isDarkTheme = isDarkTheme,
					isTextArea = true,
					focusManager = focusManager,
					text = homeViewModel.productReview.value,
					onChange = { homeViewModel.updateProductReview(it) }
				)

				Spacer(modifier = Modifier.height(24.dp))
			}
			
			// Analysis Button
			item {
				Button(
					onClick = {
						scope.launch {
							clickedAnalyze(
								focusManager = focusManager,
								viewModel = homeViewModel
							)
						}
					},
					modifier = Modifier.fillMaxWidth(),
					contentPadding = PaddingValues(12.dp),
					shape = RoundedCornerShape(16.dp),
					colors = ButtonDefaults.buttonColors(
						containerColor = Color(0xFFF97A32),
						contentColor = onPrimaryContainerLight
					),
					enabled = homeViewModel.isEnabled.value
				) {
					Row(
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.Center
					) {
						Icon(
							painter = painterResource(id = R.drawable.icon_target),
							contentDescription = null
						)
						Spacer(modifier = Modifier.width(4.dp))
						Text(
							text = stringResource(id = R.string.button_analyze).uppercase(),
							fontWeight = FontWeight.Black,
							style = MaterialTheme.typography.bodyLarge
						)
					}
				}
			}
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
						text = ".ai",
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