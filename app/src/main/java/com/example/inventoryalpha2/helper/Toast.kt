package com.example.inventoryalpha2.helper

import android.content.Context
import com.example.inventoryalpha2.R
import es.dmoral.toasty.Toasty

/**
* @param context
* */
class Toast(val context: Context) {

    fun info(msg: String){
        Toasty.custom(
            context,
            msg,
            R.drawable.ic_info,
            R.color.colorPrimary,
            Toasty.LENGTH_SHORT,
            true,
            true
        ).show()
    }

    fun warning(msg: String){
        Toasty.custom(
            context,
            msg,
            R.drawable.ic_warning,
            R.color.warning,
            Toasty.LENGTH_SHORT,
            true,
            true
        ).show()
    }

    fun error(msg: String){
        Toasty.custom(
            context,
            msg,
            R.drawable.ic_error,
            R.color.error,
            Toasty.LENGTH_SHORT,
            true,
            true
        ).show()
    }

    fun success(msg: String){
        Toasty.custom(
            context,
            msg,
            R.drawable.ic_success,
            R.color.success,
            Toasty.LENGTH_SHORT,
            true,
            true
        ).show()
    }
}