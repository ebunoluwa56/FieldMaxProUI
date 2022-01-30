package com.iyanuoluwa.fieldmaxproui

import java.util.*

class SchedulesRepository {

    val schedules = listOf(
        ScheduleListItem.Schedules(
            date = getDate(2022, Calendar.JANUARY, 14),
            "Pep Store",
            "I do not know",
            "7:45",
            "0000000000000",
            "Not started"
        ),

        ScheduleListItem.Schedules(
            date = getDate(2022, Calendar.JANUARY, 14),
            "Pep Store",
            "I do not know",
            "7:45",
            "0000000000000",
            "Not started"
        ),

        ScheduleListItem.Schedules(
            date = getDate(2022, Calendar.JANUARY, 15),
            "Pep Store",
            "I do not know",
            "7:45",
            "0000000000000",
            "Not started"
        ),
        ScheduleListItem.Schedules(
            date = getDate(2022, Calendar.JANUARY, 15),
            "Pep Store",
            "I do not know",
            "7:45",
            "0000000000000",
            "Not started"
        )
    )

}
fun getDate(year : Int, month : Int, day : Int) : Date {
    val cal = Calendar.getInstance()
    cal.clear()
    cal.set(year, month, day)
    return cal.time
}