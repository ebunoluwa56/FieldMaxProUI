package com.iyanuoluwa.fieldmaxproui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SchedulesViewModel (private val schedulesRepository: SchedulesRepository) :ViewModel() {
    fun getSchedules() : MutableList<ScheduleListItem> {
        val schedules = schedulesRepository.schedules.sortedBy{
            it.date
        }
        val groupedSchedules = schedulesRepository.schedules.groupBy {
            it.date
        }
        val scheduleListItem = mutableListOf<ScheduleListItem>()
        for (date in groupedSchedules.keys) {
            scheduleListItem.add(ScheduleListItem.DateHeader(date))
            scheduleListItem.addAll(groupedSchedules[date]!!)
        }
        return scheduleListItem
    }
}


class ViewModelFactory(private val repository: SchedulesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SchedulesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SchedulesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
