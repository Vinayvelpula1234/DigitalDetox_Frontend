package com.simats.digitaldetox

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class FocusSessionActivity : AppCompatActivity() {

    private var selectedDuration = 25 // default duration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_focus_session)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressed() }

        val duration15 = findViewById<Button>(R.id.duration_15)
        val duration25 = findViewById<Button>(R.id.duration_25)
        val duration45 = findViewById<Button>(R.id.duration_45)
        val duration60 = findViewById<Button>(R.id.duration_60)
        val duration90 = findViewById<Button>(R.id.duration_90)
        val sessionGoalInput = findViewById<EditText>(R.id.session_goal_input)
        val startFocusSessionButton = findViewById<Button>(R.id.start_focus_session_button)

        val durationButtons = mapOf(
            duration15 to 15,
            duration25 to 25,
            duration45 to 45,
            duration60 to 60,
            duration90 to 90
        )

        durationButtons.keys.forEach { button ->
            button.setOnClickListener {
                durationButtons.keys.forEach { it.isSelected = false }
                button.isSelected = true
                selectedDuration = durationButtons[button]!!
            }
        }

        // Select 25 by default
        duration25.isSelected = true

        startFocusSessionButton.setOnClickListener {
            val intent = Intent(this, CountdownActivity::class.java)
            intent.putExtra("DURATION", selectedDuration)
            intent.putExtra("SESSION_GOAL", sessionGoalInput.text.toString())
            startActivity(intent)
        }
    }
}
