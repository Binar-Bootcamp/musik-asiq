package com.binaracademy.musikasiq.data.repository.remote

import com.binaracademy.musikasiq.data.api.RetrofitClient
import com.binaracademy.musikasiq.data.api.SoundCloudService
import com.binaracademy.musikasiq.data.model.ListTrackResponse
import com.binaracademy.musikasiq.data.model.PopularTrackResponse
import com.binaracademy.musikasiq.data.model.SearchTrackResponse
import com.binaracademy.musikasiq.data.model.TrackMetaDataResponse

class SoundCloudRepositoryImpl : SoundCloudRepository {
    private val client: RetrofitClient = RetrofitClient()
    private val soundCloudService: SoundCloudService = client.instance.create(SoundCloudService::class.java)

    override suspend fun getPopularTracks(user: String?): Result<PopularTrackResponse> {
        return try {
            val playlist = soundCloudService.getPopularTracks(user ?: "https://soundcloud.com/atlantic-records-uk")
            Result.success(playlist)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    override suspend fun searchTrackByUser(term: String): Result<SearchTrackResponse> {
        return try {
            val tracks = soundCloudService.searchForTracks(term)
            Result.success(tracks)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    override suspend fun getTrackMetaData(id: String): Result<TrackMetaDataResponse> {
        return try {
            val meta = soundCloudService.getTrackMetaData(id)
            Result.success(meta)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    override suspend fun getTracks(): Result<ListTrackResponse> {
        return try {
            val tracks = soundCloudService.getTrackList()
            Result.success(tracks)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}