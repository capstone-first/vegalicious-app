package com.bangkit.vegalicious.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.vegalicious.data.ProfileRepository
import com.bangkit.vegalicious.models.Profile
import com.bangkit.vegalicious.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProfileViewModel(
	private val profileRepository: ProfileRepository,
) : ViewModel() {
	
	private val _uiState: MutableStateFlow<UiState<Profile>> = MutableStateFlow(UiState.Loading)
	
	val uiState: StateFlow<UiState<Profile>>
		get() = _uiState
	
	fun getProfile(username: String) {
		viewModelScope.launch {
			profileRepository.getProfile(username)
				.catch {
					_uiState.value = UiState.Error(it.message.toString())
				}
				.collect {
					_uiState.value = UiState.Success(it)
				}
		}
	}
}