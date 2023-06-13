package com.bangkit.vegalicious.components

import android.util.Log
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

// Reference: https://developer.android.com/jetpack/compose/text#click-with-annotation
@Composable
fun AnnotatedClickableText(text1: String, text2: String, action: () -> Unit, modifier: Modifier = Modifier) {
	val annotatedText = buildAnnotatedString {
		//append your initial text
		withStyle(
			style = SpanStyle(
				color = MaterialTheme.colorScheme.onBackground
			)
		) {
			append(text1)
			
		}
		
		//Start of the pushing annotation which you want to color and make them clickable later
		pushStringAnnotation(
			tag = text2,// provide tag which will then be provided when you click the text
			annotation = text2
		)
		//add text with your different color/style
		withStyle(
			style = SpanStyle(
				color = MaterialTheme.colorScheme.primary
			)
		) {
			append(text2)
		}
		pop()
	}
	ClickableText(
		text = annotatedText,
		onClick = { offset ->
			annotatedText.getStringAnnotations(
				tag = text2,// tag which you used in the buildAnnotatedString
				start = offset,
				end = offset
			).firstOrNull()?.let { annotation ->
				Log.d("Clicked URL", annotation.item)
				action()
			}
		}
	)
}