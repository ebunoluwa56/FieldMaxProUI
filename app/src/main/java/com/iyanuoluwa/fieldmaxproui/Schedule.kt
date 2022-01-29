package com.iyanuoluwa.fieldmaxproui

class Schedule (
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
        ) : Constants(SCHEDULES)