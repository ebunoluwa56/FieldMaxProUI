package com.iyanuoluwa.fieldmaxproui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter (
    private val items : MutableList<ScheduleListItem>
        ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        @Suppress("DUPLICATE_LABEL_IN_WHEN")
        when (viewType) {
            DateViewHolder.DATE_ADDED -> {
                val v1: View = inflater.inflate(R.layout.date_added, parent, false)
                viewHolder = DateViewHolder(v1)
            }
            ScheduleViewHolder.SCHEDULES -> {
                val v2: View = inflater.inflate(R.layout.schedules, parent, false)
                viewHolder = ScheduleViewHolder(v2)
            }
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            DateViewHolder.DATE_ADDED -> (holder as DateViewHolder).bind(
                items[position] as ScheduleListItem.DateAdded
            )
            Constants.SCHEDULES -> (holder as ScheduleViewHolder).bind(
                items[position] as ScheduleListItem.Schedules)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ScheduleListItem.DateAdded -> R.layout.date_added
            is ScheduleListItem.Schedules -> R.layout.schedules
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

    class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var dateAdded : TextView
        companion object {
            const val DATE_ADDED = 0
        }
        fun bind (date : ScheduleListItem.DateAdded) {
            dateAdded = itemView.findViewById(R.id.date)
            dateAdded.text = date.dateAdded
        }
    }

    class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            const val SCHEDULES = 0
        }
        private lateinit var placeName : TextView
        private lateinit var address : TextView
        private lateinit var time : TextView
        private lateinit var phoneNumber : TextView
        private lateinit var progress : TextView
        private lateinit var details : TextView
        private lateinit var show : TextView
        private lateinit var call : TextView
        private lateinit var visit : TextView
        private lateinit var proceed : TextView

        fun bind (schedules : ScheduleListItem.Schedules) {
            placeName = itemView.findViewById(R.id.place_name)
            address = itemView.findViewById(R.id.address)
            time = itemView.findViewById(R.id.textView4)
            phoneNumber = itemView.findViewById(R.id.textView7)
            progress = itemView.findViewById(R.id.textView11)
            details = itemView.findViewById(R.id.textView5)
            show = itemView.findViewById(R.id.textView6)
            call = itemView.findViewById(R.id.textView8)
            visit = itemView.findViewById(R.id.textView9)
            proceed = itemView.findViewById(R.id.textView10)

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
        }
    }
}