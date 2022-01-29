package com.iyanuoluwa.fieldmaxproui

sealed  class ScheduleListItem {

    data class DateAdded (
        val dateAdded : String
            ): ScheduleListItem()
    data class Schedules (
        val placeName : String,
        val address : String,
        val time : String,
        val phoneNumber : String,
        val progress : String,
        val details : String = "Details",
        val show : String = "Tap to Show",
        val call : String = "Tap to Call",
        val visit : String = "Visit outlet",
        val proceed : String = "Tap to proceed"
            ) : ScheduleListItem()
}