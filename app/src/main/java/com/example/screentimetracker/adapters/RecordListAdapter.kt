package com.example.screentimetracker.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.screentimetracker.R
import com.example.screentimetracker.data.ScreenTimeRecord

class RecordListAdapter(private val list: List<ScreenTimeRecord>) : RecyclerView.Adapter<RecordListAdapter.ResourceViewHolder>() {
    class ResourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var idTv: TextView = itemView.findViewById(R.id.idTv)
        private var minutesTv: TextView = itemView.findViewById(R.id.minutesTv)
        private var secondsTv: TextView = itemView.findViewById(R.id.secondsTv)
//        private var timestampStartTv: TextView = itemView.findViewById(R.id.timestampStartTv)
//        private var timestampEndTv: TextView = itemView.findViewById(R.id.timestampEndTv)

        private lateinit var record: ScreenTimeRecord

        @SuppressLint("SetTextI18n")
        fun onBind(record: ScreenTimeRecord){
            this.record = record

            idTv.text = "ID: " + record.id.toString()
            minutesTv.text = "Minutes: " + record.minutes.toString()
            secondsTv.text = "Seconds: " + record.seconds.toString()
//            timestampStartTv.text = "START: " + record.timeStampStart.toString()
//            timestampEndTv.text = "END: " + record.timeStampEnd.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_record, parent, false)

        return ResourceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}