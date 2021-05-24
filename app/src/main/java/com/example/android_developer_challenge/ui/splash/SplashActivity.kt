package com.example.android_developer_challenge.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.android_developer_challenge.R
import com.example.android_developer_challenge.util.FireIntent

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                FireIntent.fireMainActivity(this)
            }, 500)
        }

    }
}