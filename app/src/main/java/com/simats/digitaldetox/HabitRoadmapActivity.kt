package com.simats.digitaldetox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HabitRoadmapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_roadmap)

        findViewById<android.widget.ImageView>(R.id.back_button).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
