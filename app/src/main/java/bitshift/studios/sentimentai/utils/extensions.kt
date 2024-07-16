/*
	* GNU GENERAL PUBLIC LICENSE
	* Copyright 2024 - DEV XERO

	* Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
	* Everyone is permitted to copy and distribute verbatim copies
	* of this license document, but changing it is not allowed.
*/
package bitshift.studios.sentimentai.utils

import androidx.compose.ui.Modifier

fun Modifier.conditional(
	condition: Boolean,
	ifTrue: Modifier.() -> Modifier,
	ifFalse: (Modifier.() -> Modifier)? = null,
): Modifier {
	return if (condition) {
		then(ifTrue(Modifier))
	} else if (ifFalse != null) {
		then(ifFalse(Modifier))
	} else {
		this
	}
}