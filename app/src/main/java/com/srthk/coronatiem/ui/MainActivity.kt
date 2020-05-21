package com.srthk.coronatiem.ui

import android.content.res.Configuration
import android.os.Bundle
import android.util.TypedValue
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.srthk.coronatiem.R
import com.srthk.coronatiem.util.InternetNotAvailableException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
