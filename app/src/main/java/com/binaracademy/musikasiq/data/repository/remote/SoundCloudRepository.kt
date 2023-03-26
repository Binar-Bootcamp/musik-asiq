package com.binaracademy.musikasiq.data.repository.remote

import com.binaracademy.musikasiq.data.model.PopularTrackResponse
import com.binaracademy.musikasiq.data.model.SearchTrackResponse
import com.binaracademy.musikasiq.data.model.TrackMetaDataResponse

interface SoundCloudRepository {

    suspend fun getPopularTracks(user: String?): Result<PopularTrackResponse>

    suspend fun searchTrackByUser(term: String): Result<SearchTrackResponse>

    suspend fun getTrackMetaData(id: String): Result<TrackMetaDataResponse>
}