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

        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val sessionGoalInput = findViewById<EditText>(R.id.session_goal_input)
        val startFocusSessionButton = findViewById<Button>(R.id.start_focus_session_button)

        val etDurationInput = findViewById<android.widget.EditText>(R.id.et_duration_input)
        val sliderDuration = findViewById<com.google.android.material.slider.Slider>(R.id.slider_duration)

        // Function to update UI safely without triggering loops
        fun updateDurationFromSlider(duration: Int) {
            selectedDuration = duration
            if (etDurationInput.text.toString() != duration.toString()) {
                etDurationInput.setText(duration.toString())
                etDurationInput.setSelection(etDurationInput.text.length)
            }
        }

        fun updateDurationFromInput(duration: Int) {
            var validDuration = duration
            if (validDuration < 1) validDuration = 1
            if (validDuration > 180) validDuration = 180
            
            selectedDuration = validDuration
            sliderDuration.value = validDuration.toFloat()
        }

        // Slider Listener
        sliderDuration.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                updateDurationFromSlider(value.toInt())
            }
        }

        // EditText Listener
        etDurationInput.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {
                if (s.isNullOrEmpty()) return
                val input = s.toString().toIntOrNull()
                if (input != null && sliderDuration.value.toInt() != input) {
                    // Only update if value actually changed to avoid loop
                     // We don'tclamp immediately while typing to allow "1" -> "15" without it snapping to 5
                     // But we should clamp for the slider visual
                     var sliderValue = input.toFloat()
                     if (sliderValue < 1f) sliderValue = 1f
                     if (sliderValue > 180f) sliderValue = 180f
                     sliderDuration.value = sliderValue
                     selectedDuration = input // Allow the actual value, but clamp execution time later if needed
                }
            }
        })

        // Validation on focus lost
        etDurationInput.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val input = etDurationInput.text.toString().toIntOrNull() ?: 25
                updateDurationFromInput(input)
                etDurationInput.setText(selectedDuration.toString())
            }
        }

        startFocusSessionButton.setOnClickListener {
            // Read directly from input to ensure WYSIWYG (What You See Is What You Get)
            val inputStr = etDurationInput.text.toString()
            var finalDuration = inputStr.toIntOrNull() ?: 25
            
            // Clamp value
            if (finalDuration < 1) finalDuration = 1
            if (finalDuration > 180) finalDuration = 180
            
            val intent = Intent(this, CountdownActivity::class.java)
            intent.putExtra("DURATION", finalDuration)
            intent.putExtra("SESSION_GOAL", sessionGoalInput.text.toString())
            startActivity(intent)
        }
    }
}
