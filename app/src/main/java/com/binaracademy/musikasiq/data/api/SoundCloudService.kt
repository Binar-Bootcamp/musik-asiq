package com.binaracademy.musikasiq.data.api

import com.binaracademy.musikasiq.data.model.PopularTrackResponse
import com.binaracademy.musikasiq.data.model.SearchTrackResponse
import com.binaracademy.musikasiq.data.model.TrackMetaDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SoundCloudService {

    @GET("/v1/user/toptracks")
    suspend fun getPopularTracks(
        @Query("user") user: String
    ): PopularTrackResponse

    @GET("/v1/search/tracks")
    suspend fun searchForTracks(
        @Query("term") term: String
    ): SearchTrackResponse


    @GET("/v1/track/metadata")
    suspend fun getTrackMetaData(
        @Query("track") trackId: String
    ): TrackMetaDataResponse
}