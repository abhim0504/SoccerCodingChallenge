package com.ix.codingchallenge.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ix.codingchallenge.R
import com.ix.codingchallenge.databinding.FragmentLeagueListBinding
import com.ix.codingchallenge.model.LeagueDetail
import com.ix.codingchallenge.util.AppConstants
import com.ix.codingchallenge.util.MyApplication
import com.ix.codingchallenge.util.Status
import com.ix.codingchallenge.view.adapters.fixtures.FixturesStickyHeaderDecoration
import com.ix.codingchallenge.util.setDivider
import com.ix.codingchallenge.view.adapters.fixtures.FixturesAdapter
import com.ix.codingchallenge.viewmodel.LeaguesViewModel
import kotlinx.android.synthetic.main.fragment_league_list.*
import javax.inject.Inject

/**
 * A Fixtures fragment containing a list of competitions (Along with Date & Week)
 */
class FixturesFragment : Fragment() {

    @Inject
    lateinit var leaguesViewModel: LeaguesViewModel
    private val fixturesAdapter = FixturesAdapter()
    lateinit private var viewRoot: FragmentLeagueListBinding

    override fun onAttach(context: Context) {
        // Make Dagger instantiate @Inject fields -- fixturesViewModel
        (requireActivity().application as MyApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewRoot =
            FragmentLeagueListBinding.inflate(inflater, container, false)

        getFixturesFromAPI()

        observerListChanges()

        return viewRoot.root
    }

    private fun getFixturesFromAPI() {
        leaguesViewModel.getFixtures().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    // Handle if there is no data
                    if (it.data?.size == 0) {
                        txtNoData.visibility = View.VISIBLE
                        rv_leaguesList.visibility = View.GONE
                    } else {
                        rv_leaguesList.visibility = View.VISIBLE
                        leaguesViewModel.fixturesFilteredList.value = it.data
                        leaguesViewModel.fixturesList.value = it.data
                    }
                }
                Status.ERROR -> {
                    // Generic error handling in case of API failure or No internet
                    progressBar.visibility = View.GONE
                    rv_leaguesList.visibility = View.VISIBLE
                    txtNoData.visibility = View.GONE

                    Snackbar.make(
                        rv_leaguesList, R.string.error_generic,
                        Snackbar.LENGTH_INDEFINITE
                    )
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    rv_leaguesList.visibility = View.GONE
                    txtNoData.visibility = View.GONE
                }
            }
        })
    }

    private fun observerListChanges() {
        leaguesViewModel.fixturesFilteredList.observe(this, {
            setAdapter(it)
        })

        leaguesViewModel.queryData.observe(this, { queryString ->
            leaguesViewModel.fixturesFilteredList.value =
                leaguesViewModel.fixturesList.value?.filter {
                    it.competitionStage.competition.name.toLowerCase()
                        .contains(queryString.toLowerCase())
                }
        })

    }

    private fun setAdapter(data: List<LeagueDetail>?) {
        if (data?.size == 0) {
            txtNoData.text = AppConstants.NORESULTS
            rv_leaguesList.visibility = View.GONE
            txtNoData.visibility = View.VISIBLE
            return
        }
        rv_leaguesList.visibility = View.VISIBLE
        txtNoData.visibility = View.GONE

        fixturesAdapter.groupedLeagueData = leaguesViewModel.groupByMonth(data)

        viewRoot.rvLeaguesList.setDivider(R.drawable.list_divider)
        viewRoot.rvLeaguesList.addItemDecoration(
            FixturesStickyHeaderDecoration(fixturesAdapter, viewRoot.root)
        )
        viewRoot.rvLeaguesList.layoutManager = LinearLayoutManager(requireContext())
        viewRoot.rvLeaguesList.adapter = fixturesAdapter
    }

    companion object {
        /**
         * Returns a new instance of this fragment
         */
        @JvmStatic
        fun newInstance(): FixturesFragment {
            return FixturesFragment()
        }
    }
}