package com.example.inventoryalpha2.data

import android.content.Context

class Preference(context: Context, name: Name) {
    private val pref = context.getSharedPreferences(name.toString(), Context.MODE_PRIVATE)

    fun writeString(key: Key, value: String){
        with(pref.edit()){
            putString(key.toString(), value)
            commit()
        }
    }

    fun readString(key: Key): String? {
        return pref.getString(key.toString(), "")
    }

    fun writeBool(key: Key, value: Boolean){
        with(pref.edit()){
            putBoolean(key.toString(), value)
            commit()
        }
    }

    fun readBool(key: Key, defValue: Boolean): Boolean {
        return pref.getBoolean(key.toString(), defValue)
    }

    enum class Name{
        BLUETOOTH
    }

    enum class Key{
        BLUETOOTH_ADDRESS,
        BLUETOOTH_NAME,
        AUTO_RECONNECT
    }
}