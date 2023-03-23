package com.binaracademy.musikasiq.data.repository.remote

import com.binaracademy.musikasiq.data.model.PopularTrackResponse

interface SoundCloudRepository {

    suspend fun getPopularTracks(user: String?): Result<PopularTrackResponse>

}