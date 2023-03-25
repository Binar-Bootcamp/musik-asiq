package com.binaracademy.musikasiq.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binaracademy.musikasiq.data.model.PopularTrackResponse
import com.binaracademy.musikasiq.data.repository.remote.SoundCloudRepository
import com.binaracademy.musikasiq.data.repository.remote.SoundCloudRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel(
	private val soundCloudRepository: SoundCloudRepository = SoundCloudRepositoryImpl()
) : ViewModel() {
	private val userPlaylist = MutableLiveData<Result<PopularTrackResponse>>()
	
	fun getPopularTracks(): LiveData<Result<PopularTrackResponse>> = userPlaylist
	
	fun loadPopularTracks(user: String?) {
		viewModelScope.launch {
			userPlaylist.value = soundCloudRepository.getPopularTracks(user)
			
		}
	}
}