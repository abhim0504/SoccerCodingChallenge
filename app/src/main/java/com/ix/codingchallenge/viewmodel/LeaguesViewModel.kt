package com.ix.codingchallenge.viewmodel

import androidx.lifecycle.*
import com.ix.codingchallenge.model.LeagueDetail
import com.ix.codingchallenge.network.ApiHelper
import com.ix.codingchallenge.util.Resource
import kotlinx.coroutines.Dispatchers
import java.time.format.TextStyle
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaguesViewModel @Inject constructor(private val apiHelper: ApiHelper) : ViewModel() {

    var fixturesFilteredList: MutableLiveData<List<LeagueDetail>> = MutableLiveData()
    var resultsFilteredList: MutableLiveData<List<LeagueDetail>> = MutableLiveData()

    var fixturesList: MutableLiveData<List<LeagueDetail>> = MutableLiveData()
    var resultsList: MutableLiveData<List<LeagueDetail>> = MutableLiveData()

    var queryData: MutableLiveData<String> = MutableLiveData()

    // Group LeagueDetails list based on Month
    fun groupByMonth(data: List<LeagueDetail>?): Map<String, List<LeagueDetail>>{
        val groupedBooks: Map<String, List<LeagueDetail>> =
            data!!.groupBy { book ->
                book.dateFormatted.month
                    .getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                    .toUpperCase(Locale.getDefault()) + " " + book.dateFormatted.year
            }
        return groupedBooks.toSortedMap()
    }

    // Method to fetch data from API
    fun getFixtures() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = apiHelper.getFixtures()
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    // Method to fetch data from API
    fun getResults() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = apiHelper.getResults()
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}