package com.ix.codingchallenge.dagger

import com.ix.codingchallenge.network.RetrofitBuilder
import com.ix.codingchallenge.view.activities.MainActivity
import com.ix.codingchallenge.view.fragments.FixturesFragment
import com.ix.codingchallenge.view.fragments.ResultsFragment
import dagger.Component
import javax.inject.Singleton


/**
 * Dagger Application Component to inject the places of dependencies
 */
@Singleton
@Component(modules = [RetrofitBuilder::class])
interface ApplicationComponent {
    // This tells Dagger the Activities/Fragments requests injection so the graph needs to
    // satisfy all the dependencies of the field
    fun inject(activity: MainActivity)
    fun inject(fragment: FixturesFragment)
    fun inject(fragment: ResultsFragment)
}