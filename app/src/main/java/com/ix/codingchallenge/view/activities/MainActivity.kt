package com.ix.codingchallenge.view.activities

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.ix.codingchallenge.R
import com.ix.codingchallenge.util.MyApplication
import com.ix.codingchallenge.view.adapters.LeaguesPagerAdapter
import com.ix.codingchallenge.viewmodel.LeaguesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(){

    @Inject
    lateinit var leagueViewModel: LeaguesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // Make Dagger instantiate @Inject fields -- leagueViewModel
        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View Pager Adapter to render Fixtures & Results list
        setViewPagerAdapter()

        // Configure & Manage operations for Search
        configureSearch()
    }

    private fun configureSearch() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                leagueViewModel.queryData.value = query
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                leagueViewModel.queryData.value = searchView.query.toString()
                return true
            }
        })
    }

    private fun setViewPagerAdapter() {
        val leaguesPagerAdapter = LeaguesPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = leaguesPagerAdapter
        tabs.setupWithViewPager(view_pager)
    }
}