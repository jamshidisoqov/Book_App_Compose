package uz.gita.book_app_compose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Created by Jamshid Isoqov an 10/9/2022
@HiltAndroidApp
class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}