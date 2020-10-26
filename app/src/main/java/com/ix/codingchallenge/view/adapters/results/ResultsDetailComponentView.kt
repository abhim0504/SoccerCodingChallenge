package com.ix.codingchallenge.view.adapters.results

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import com.ix.codingchallenge.R
import com.ix.codingchallenge.databinding.ViewLeagueDetailsComponentBinding
import com.ix.codingchallenge.databinding.ViewResultsDetailListItemBinding
import com.ix.codingchallenge.model.LeagueDetail
import com.ix.codingchallenge.util.AppConstants
import com.ix.codingchallenge.util.Utils
import com.ix.codingchallenge.util.requestLayoutForChangedDataset

class ResultsDetailComponentView : ConstraintLayout {

    private lateinit var binding: ViewLeagueDetailsComponentBinding
    private lateinit var resultItemBinding: ViewResultsDetailListItemBinding
    private lateinit var adapter: ResultDetailAdapter

    var leaguesList: List<LeagueDetail> = emptyList()
        set(value) {
            field = value
            onItemsUpdated()
        }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    private fun init(context: Context) {
        binding =
            ViewLeagueDetailsComponentBinding.inflate(LayoutInflater.from(context), this, true)
        adapter = ResultDetailAdapter(context)
        binding.leagueDetailsList.adapter = adapter
    }

    private fun onItemsUpdated() {
        adapter.notifyDataSetChanged()
        binding.leagueDetailsList.requestLayoutForChangedDataset()
    }

    inner class ResultDetailAdapter(private val context: Context) : BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val league: LeagueDetail = leaguesList[position]
            var view: View? = convertView

            if (view == null) {
                view = LayoutInflater.from(context)
                    .inflate(R.layout.view_results_detail_list_item, parent, false)
                resultItemBinding = ViewResultsDetailListItemBinding.bind(view)
                view.tag = resultItemBinding
            } else {
                resultItemBinding = view.tag as ViewResultsDetailListItemBinding
            }

            resultItemBinding.apply {
                tvName.text = league.competitionStage.competition.name
                tvMatchDateAndTime.text = league.venue.name + " | " +
                        Utils.getDateStringFormat(league.dateFormatted)
                tvHomeTeam.text = league.homeTeam.name
                tvAwayTeam.text = league.awayTeam.name

                if (league.score.winner.equals(AppConstants.HOME)) {
                    txtHomeScore.text = "*" + league.score.home.toString()
                    txtAwayScore.text = league.score.away.toString()
                    txtHomeScore.setTextColor(context.getColor(R.color.colorPrimary))
                    txtAwayScore.setTextColor(context.getColor(R.color.black))
                } else {
                    txtHomeScore.text = league.score.home.toString()
                    txtAwayScore.text = "*" + league.score.away.toString()
                    txtHomeScore.setTextColor(context.getColor(R.color.black))
                    txtAwayScore.setTextColor(context.getColor(R.color.colorPrimary))
                }
            }

            return resultItemBinding.root
        }

        override fun getItem(position: Int): Any {
            return leaguesList[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return leaguesList.size
        }

        override fun isEnabled(position: Int): Boolean {
            return false
        }
    }
}