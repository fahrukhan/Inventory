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

    enum class Name{
        BLUETOOTH
    }

    enum class Key{
        BLUETOOTH_ADDRESS
    }
}