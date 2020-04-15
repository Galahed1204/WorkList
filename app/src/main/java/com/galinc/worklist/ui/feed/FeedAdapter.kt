package com.galinc.worklist.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.galinc.worklist.R
import com.galinc.worklist.domain.entity.Feed

class FeedAdapter(var items:List<Feed>):RecyclerView.Adapter<FeedAdapter.FeedHolder>(){

    override fun getItemCount(): Int = items.size

    inner class FeedHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textTitle = itemView.findViewById<TextView>(R.id.textFeedHead)
        private val textBody = itemView.findViewById<TextView>(R.id.textFeedBody)

        fun bind(item: Feed) {
            textTitle.text = item.title
            textBody.text = item.body
        }
    }

    override fun onBindViewHolder(holder: FeedHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedHolder
            = FeedHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task_feed, parent, false))

}