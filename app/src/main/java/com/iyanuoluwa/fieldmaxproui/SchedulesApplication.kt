package com.iyanuoluwa.fieldmaxproui

import android.app.Application

class SchedulesApplication : Application() {
    val repository by lazy { SchedulesRepository() }
}