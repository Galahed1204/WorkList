package com.galinc.worklist.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.galinc.worklist.R
import com.galinc.worklist.domain.entity.MainTask

class MainTaskAdapter(var items:List<MainTask>, val callback: Callback):RecyclerView.Adapter<MainTaskAdapter.MainHolder>(){

    override fun getItemCount() =items.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task_main, parent, false))

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textOfTask = itemView.findViewById<TextView>(R.id.textOfTask)
        private val checkBoxCompleted = itemView.findViewById<CheckBox>(R.id.checkBoxCompleted)

        fun bind(item: MainTask) {
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

    fun setItemList(item: List<MainTask>){
        this.items = item
        notifyDataSetChanged()
    }

    interface Callback {
        fun onItemClicked(item: MainTask)

        fun onItemChecked(item: MainTask)
    }
}