package com.ix.codingchallenge.network

import com.ix.codingchallenge.model.LeagueDetail
import retrofit2.http.GET

interface ApiService {
    // GET network API call to get questions data from the API
    @GET("fixtures.json")
    suspend fun getFixtures(): List<LeagueDetail>

    @GET("results.json")
    suspend fun getResults(): List<LeagueDetail>
}