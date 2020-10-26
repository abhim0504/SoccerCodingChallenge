package com.ix.codingchallenge.network

import javax.inject.Inject

/**
 * Helper Class in which we can add cache logic in future
 */
class ApiHelper @Inject constructor(private val apiService: ApiService) {
    suspend fun getFixtures() = apiService.getFixtures()
    suspend fun getResults() = apiService.getResults()
}