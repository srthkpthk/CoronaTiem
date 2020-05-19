package com.srthk.coronatiem.util

import android.content.Context
import android.widget.Toast

object Utils {
    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}