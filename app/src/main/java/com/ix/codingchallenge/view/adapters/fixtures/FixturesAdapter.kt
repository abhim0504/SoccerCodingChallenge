package com.ix.codingchallenge.view.adapters.fixtures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ix.codingchallenge.databinding.FixturesViewListItemBinding
import com.ix.codingchallenge.model.LeagueDetail

class FixturesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var leaguesHeader: List<String> = listOf()

    var groupedLeagueData: Map<String, List<LeagueDetail>> = emptyMap()
        set(value) {
            field = value
            leaguesHeader = groupedLeagueData.keys.toList()
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val viewBinding: FixturesViewListItemBinding =
            FixturesViewListItemBinding.inflate(layoutInflater, parent, false)
        return LeagueViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position >= 0 && position < leaguesHeader.size) {
            (holder as LeagueViewHolder).bind(leaguesHeader[position])
        }
    }

    override fun getItemCount() = leaguesHeader.size

    fun getHeaderForCurrentPosition(position: Int) = if (position in leaguesHeader.indices) {
        leaguesHeader[position]
    } else {
        ""
    }

    inner class LeagueViewHolder(private val viewBinding: FixturesViewListItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(header: String) {
            viewBinding.tvHeader.text = header
            groupedLeagueData[header]?.let { leagues ->
                viewBinding.leagueDetailsView.leagueDetailList = leagues
            }
        }
    }
}