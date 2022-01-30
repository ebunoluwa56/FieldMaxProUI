package com.iyanuoluwa.fieldmaxproui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class RecyclerViewAdapter (
    private val items : List<ScheduleListItem>
        ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val DATE_HEADER = 0
        const val SCHEDULES = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        @Suppress("DUPLICATE_LABEL_IN_WHEN")
        when (viewType) {
            DATE_HEADER -> {
                val v1: View = inflater.inflate(R.layout.date_header, parent, false)
                viewHolder = DateViewHolder(v1)
            }
            SCHEDULES -> {
                val v2: View = inflater.inflate(R.layout.schedules, parent, false)
                viewHolder = ScheduleViewHolder(v2)
            }
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            DATE_HEADER -> (holder as DateViewHolder).bind(
                items[position] as ScheduleListItem.DateHeader
            )
            SCHEDULES -> (holder as ScheduleViewHolder).bind(
                items[position] as ScheduleListItem.Schedules)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ScheduleListItem.DateHeader -> DATE_HEADER
            is ScheduleListItem.Schedules -> SCHEDULES
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

    class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var dateHeader : TextView
        fun bind (date : ScheduleListItem.DateHeader) {
            dateHeader = itemView.findViewById(R.id.date)
            dateHeader.text = SimpleDateFormat("EEE, MMM dd").format(date.dateHeader)
        }
    }

    class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val placeName : TextView = itemView.findViewById(R.id.place_name)
        private val address : TextView = itemView.findViewById(R.id.address)
        private val time : TextView = itemView.findViewById(R.id.tap_to_show)
        private val phoneNumber : TextView = itemView.findViewById(R.id.number)
        private val progress : TextView = itemView.findViewById(R.id.progress)
        private val details : TextView = itemView.findViewById(R.id.details)
        private val show : TextView = itemView.findViewById(R.id.tap_to_show)
        private val call :TextView = itemView.findViewById(R.id.tap_to_call)
        private val visit : TextView = itemView.findViewById(R.id.visit)
        private val proceed : TextView = itemView.findViewById(R.id.tap_to_visit)
        private val expandableLayout : ConstraintLayout = itemView.findViewById(R.id.expanded_layout)
        private val expandButton : ImageButton = itemView.findViewById(R.id.expandButton)

        fun bind (schedules : ScheduleListItem.Schedules) {
            placeName.text = schedules.placeName
            address.text = schedules.address
            time.text = schedules.time
            phoneNumber.text = schedules.phoneNumber
            progress.text = schedules.progress
            details.text = schedules.details
            show.text = schedules.show
            call.text = schedules.call
            visit.text = schedules.visit
            proceed.text = schedules.proceed
            val isExpandable : Boolean = schedules.isExpandable
            expandableLayout.visibility = if (isExpandable) View.GONE else View.VISIBLE
            expandButton.setOnClickListener {
                val expand = schedules
                expand.isExpandable = !expand.isExpandable
            }
        }
    }
}