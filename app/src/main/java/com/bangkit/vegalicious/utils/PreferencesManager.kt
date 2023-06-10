package com.bangkit.vegalicious.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

//object PreferencesManager {
//	private const val PREFERENCES_NAME = "user_preferences"
//	private val KEY_USERNAME = stringPreferencesKey("username")
//	private val KEY_API_KEY = stringPreferencesKey("api_key")
//
//	private lateinit var dataStore: DataStore<Preferences>
//
//	fun initialize(context: Context) {
//		dataStore = context.createDataStore(name = PREFERENCES_NAME)
//	}
//
//	suspend fun saveUsername(username: String) {
//		dataStore.edit { preferences ->
//			preferences[KEY_USERNAME] = username
//		}
//	}
//
//	suspend fun saveApiKey(apiKey: String) {
//		dataStore.edit { preferences ->
//			preferences[KEY_API_KEY] = apiKey
//		}
//	}
//
//	val usernameFlow: Flow<String?> = dataStore.data
//		.catch { exception ->
//			if (exception is IOException) {
//				emit(emptyPreferences())
//			} else {
//				throw exception
//			}
//		}
//		.map { preferences ->
//			preferences[KEY_USERNAME]
//		}
//
//	val apiKeyFlow: Flow<String?> = dataStore.data
//		.catch { exception ->
//			if (exception is IOException) {
//				emit(emptyPreferences())
//			} else {
//				throw exception
//			}
//		}
//		.map { preferences ->
//			preferences[KEY_API_KEY]
//		}
//}