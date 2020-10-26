package com.ix.codingchallenge.util

import android.app.Application
import com.ix.codingchallenge.dagger.ApplicationComponent
import com.ix.codingchallenge.dagger.DaggerApplicationComponent

class MyApplication : Application() {
    // Reference to the application graph that is used across the whole app
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}