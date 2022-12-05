package uz.gita.book_app_compose.utils

import org.orbitmvi.orbit.ContainerHost

// Created by Jamshid Isoqov an 9/21/2022
interface BaseViewModel<INTENT : Any, STATE : Any, SIDE_EFFECT : Any> :
    ContainerHost<STATE, SIDE_EFFECT> {

    fun onEventDispatcher(intent: INTENT)

}