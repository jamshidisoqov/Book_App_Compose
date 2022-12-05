package uz.gita.book_app_compose.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import uz.gita.book_app_compose.App


// Created by Jamshid Isoqov an 10/11/2022

fun hasConnection(): Boolean = App.instance.isAvailableNetwork()

private fun Context.isAvailableNetwork(): Boolean {
    val result: Boolean
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
    result = when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
    return result
}