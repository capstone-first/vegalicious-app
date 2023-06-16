package com.bangkit.vegalicious.utils

fun calculateBMI(weightKg: Float, heightCm: Float): Pair<Float, String> {
	
	val heightM = heightCm/100
	val bmi = weightKg / (heightM * heightM)
	
	val status = when {
		bmi < 18.5f -> "Underweight"
		bmi < 25f -> "Normal"
		bmi < 30 -> "Overweight"
		else -> "Obese"
	}
	
	return bmi to status
}

fun decodeSlash(input: String) = input.replace("&&", "/")

fun encodeSlash(input: String): String = input.replace("/", "&&")