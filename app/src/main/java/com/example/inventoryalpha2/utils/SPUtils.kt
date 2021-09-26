package com.example.inventoryalpha2.utils

import android.content.Context
import android.content.SharedPreferences

class SPUtils {
    private val mInstance: SPUtils? = null
    private var sp: SharedPreferences? = null


    companion object {
        const val AUTO_RECONNECT = "autoReconnect"
        const val DISCONNECT_TIME = "disconnectTime"
        const val DISCONNECT_TIME_INDEX = "disconnectTimeIndex"
    }
}