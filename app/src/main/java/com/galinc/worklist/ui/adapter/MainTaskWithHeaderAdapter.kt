package com.galinc.worklist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.galinc.worklist.R
import com.galinc.worklist.domain.entity.MainTaskWithHeader

class MainTaskWithHeaderAdapter(var items:List<MainTaskWithHeader>, val callback: Callback):

    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0 ) return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task_main, parent, false))
        return HeaderHolder(LayoutInflater.from(parent.context).inflate(R.layout.header_task_main, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (items[position].isHeader){
            val viewHolder = holder as HeaderHolder
            viewHolder.bind(item = items[position])
        }else{
            val viewHolder = holder as MainHolder
            viewHolder.bind(item = items[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].isHeader) 1 else 0
    }

    fun setItemList(item: List<MainTaskWithHeader>){
        this.items = item
        notifyDataSetChanged()
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textOfTask = itemView.findViewById<TextView>(R.id.textOfTask)
        private val checkBoxCompleted = itemView.findViewById<CheckBox>(R.id.checkBoxCompleted)

        fun bind(item: MainTaskWithHeader) {
            textOfTask.text = item.textOfTask
            checkBoxCompleted.isChecked = item.completed
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])
            }
            checkBoxCompleted.setOnClickListener{
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemChecked(items[adapterPosition])
            }
        }
    }

    inner class HeaderHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val textOfHeader = itemView.findViewById<TextView>(R.id.header_text)

        fun bind(item:MainTaskWithHeader){
            textOfHeader.text = item.header
        }
    }

    interface Callback {
        fun onItemClicked(item: MainTaskWithHeader)

        fun onItemChecked(item: MainTaskWithHeader)
    }
}