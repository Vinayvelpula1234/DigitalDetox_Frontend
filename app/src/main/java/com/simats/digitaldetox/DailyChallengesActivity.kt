package com.simats.digitaldetox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DailyChallengesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_challenges)

        findViewById<android.widget.ImageView>(R.id.back_button).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
