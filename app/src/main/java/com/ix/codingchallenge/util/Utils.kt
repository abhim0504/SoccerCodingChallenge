package com.ix.codingchallenge.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Utils class to maintain reusable methods used across the application
 */
class Utils {

    companion object {

        /*
        * Method to format Date from String format to LocalDateTime
        */
        fun getDateFormat(dateInString: String): LocalDateTime{
            val inputFormatter: DateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
            return LocalDateTime.parse(dateInString, inputFormatter)
        }

        /*
        * Method to format date from LocalDateTime format to Readable String
        */
        fun getDateStringFormat(localDate: LocalDateTime): String{
            val dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")
            return localDate.format(dateTimeFormatter)
        }

        /**
         * Method to check if the mobile is connected to internet or not
         */
        fun isInternetConnected(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                }
            }
            return false
        }
    }
}