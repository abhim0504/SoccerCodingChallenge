package com.ix.codingchallenge.view.adapters.fixtures

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import com.ix.codingchallenge.R
import com.ix.codingchallenge.databinding.ViewLeagueDetailsComponentBinding
import com.ix.codingchallenge.databinding.ViewFixturesDetailListItemBinding
import com.ix.codingchallenge.model.LeagueDetail
import com.ix.codingchallenge.util.AppConstants
import com.ix.codingchallenge.util.Utils
import com.ix.codingchallenge.util.requestLayoutForChangedDataset
import java.time.format.TextStyle
import java.util.*

class FixtureDetailComponentView : ConstraintLayout {

    private lateinit var binding: ViewLeagueDetailsComponentBinding
    private lateinit var fixtureItemBinding: ViewFixturesDetailListItemBinding
    private lateinit var adapter: FixtureDetailAdapter

    var leagueDetailList: List<LeagueDetail> = emptyList()
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
        binding = ViewLeagueDetailsComponentBinding.inflate(LayoutInflater.from(context), this, true)
        adapter = FixtureDetailAdapter(context)
        binding.leagueDetailsList.adapter = adapter
    }

    private fun onItemsUpdated() {
        adapter.notifyDataSetChanged()
        binding.leagueDetailsList.requestLayoutForChangedDataset()
    }

    inner class FixtureDetailAdapter(private val context: Context) : BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val league: LeagueDetail = leagueDetailList[position]
            var view: View? = convertView

            if (view == null) {
                view = LayoutInflater.from(context)
                    .inflate(R.layout.view_fixtures_detail_list_item, parent, false)
                fixtureItemBinding = ViewFixturesDetailListItemBinding.bind(view)
                view.tag = fixtureItemBinding
            } else {
                fixtureItemBinding = view.tag as ViewFixturesDetailListItemBinding
            }

            fixtureItemBinding.apply {
                tvName.text = league.competitionStage.competition.name
                tvMatchDateAndTime.text = league.venue.name + " | " +
                        Utils.getDateStringFormat(league.dateFormatted)
                tvHomeTeam.text = league.homeTeam.name
                tvAwayTeam.text = league.awayTeam.name
                txtDate.text = league.dateFormatted.dayOfMonth.toString()
                txtWeek.text = league.dateFormatted.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                if(league.state == AppConstants.POSTPONED){
                    txtPostponed.visibility = View.VISIBLE
                }else {
                    txtPostponed.visibility = View.INVISIBLE
                }
            }

            return fixtureItemBinding.root
        }

        override fun getItem(position: Int): Any {
            return leagueDetailList[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return leagueDetailList.size
        }

        override fun isEnabled(position: Int): Boolean {
            return false
        }
    }
}