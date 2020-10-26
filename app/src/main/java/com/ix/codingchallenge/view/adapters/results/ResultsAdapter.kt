package com.ix.codingchallenge.view.adapters.results

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ix.codingchallenge.databinding.ResultsViewListItemBinding
import com.ix.codingchallenge.model.LeagueDetail

class ResultsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var bookHeaders: List<String> = listOf()

    var bookData: Map<String, List<LeagueDetail>> = emptyMap()
        set(value) {
            field = value
            bookHeaders = bookData.keys.toList()
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val viewBinding: ResultsViewListItemBinding =
            ResultsViewListItemBinding.inflate(layoutInflater, parent, false)
        return BookViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position >= 0 && position < bookHeaders.size) {
            (holder as BookViewHolder).bind(bookHeaders[position])
        }
    }

    override fun getItemCount() = bookHeaders.size

    fun getHeaderForCurrentPosition(position: Int) = if (position in bookHeaders.indices) {
        bookHeaders[position]
    } else {
        ""
    }

    inner class BookViewHolder(private val viewBinding: ResultsViewListItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(header: String) {
            viewBinding.tvHeader.text = header
            bookData[header]?.let { books ->
                viewBinding.leagueDetailsView.leaguesList = books
            }
        }
    }
}