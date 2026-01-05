package com.simats.digitaldetox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ProgressTrackingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_tracking)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        findViewById<androidx.cardview.widget.CardView>(R.id.card_view_analytics).setOnClickListener {
            startActivity(android.content.Intent(this, AppUsageActivity::class.java))
        }

        findViewById<androidx.cardview.widget.CardView>(R.id.card_achievements).setOnClickListener {
            startActivity(android.content.Intent(this, RewardsActivity::class.java))
        }
    }
}
