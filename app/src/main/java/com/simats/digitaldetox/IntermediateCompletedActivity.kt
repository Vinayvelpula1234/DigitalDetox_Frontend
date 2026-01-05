package com.simats.digitaldetox

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class IntermediateCompletedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intermediate_completed)

        findViewById<android.view.View>(R.id.back_button).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<android.widget.TextView>(R.id.tab_active).setOnClickListener {
            // Navigate back to overview (active)
            finish()
        }

        findViewById<android.widget.Button>(R.id.btn_try_advanced_mastery).setOnClickListener {
            startActivity(Intent(this, AdvancedMasteryActivity::class.java))
        }

        findViewById<android.widget.TextView>(R.id.link_back_to_daily_challenges).setOnClickListener {
            startActivity(Intent(this, DailyChallengesActivity::class.java))
        }
    }
}
