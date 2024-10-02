package com.wendy.wendy_article

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            val splashScreen = installSplashScreen()
            super.onCreate(savedInstanceState)

            splashScreen.setKeepOnScreenCondition {
                Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 1000)
            false
        }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
}