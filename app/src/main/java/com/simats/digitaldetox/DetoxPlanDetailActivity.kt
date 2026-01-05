package com.simats.digitaldetox

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetoxPlanDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detox_plan_detail)

        val planType = intent.getStringExtra("plan_type") ?: "beginner"
        
        // Update UI based on plan type if needed
        
        findViewById<android.widget.ImageView>(R.id.back_button).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<android.widget.Button>(R.id.btn_view_habit_roadmap).setOnClickListener {
            startActivity(Intent(this, HabitRoadmapActivity::class.java))
        }

        findViewById<android.widget.Button>(R.id.btn_start_daily_checklist).setOnClickListener {
            startActivity(Intent(this, DailyChecklistActivity::class.java))
        }
    }
}
