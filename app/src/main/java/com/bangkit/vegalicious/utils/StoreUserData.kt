package com.bangkit.vegalicious.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserData(private val context: Context) {
	
	companion object {
		private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserData")
		val AUTH_KEY = stringPreferencesKey("auth_key")
		val EMAIL = stringPreferencesKey("email")
		val USERNAME = stringPreferencesKey("username")
	}
	
	val getAuthKey: Flow<String?> =
		context.dataStore.data.map { preferences ->
			preferences[AUTH_KEY] ?: ""
		}
	
	suspend fun saveAuthKey(key: String) {
		context.dataStore.edit { preferences ->
			preferences[AUTH_KEY] = key
		}
	}
	
	val getUsername: Flow<String?> =
		context.dataStore.data.map { preferences ->
			preferences[USERNAME] ?: ""
		}
	
	suspend fun saveUsername(username: String) {
		context.dataStore.edit { preferences ->
			preferences[USERNAME] = username
		}
	}
}