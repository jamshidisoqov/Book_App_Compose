package uz.gita.book_app_compose.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import uz.gita.book_app_compose.utils.SharedPreference
import javax.inject.Inject

// Created by Jamshid Isoqov an 9/23/2022
class MySharedPref @Inject constructor(
    ctx: Context,
    sharedPreferences: SharedPreferences
) : SharedPreference(ctx, sharedPreferences) {

    var token: String by Strings("")

    var phone: String by Strings()

    var name: String by Strings("Username")


}