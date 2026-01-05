package com.simats.digitaldetox

import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DailyChecklistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_checklist)

        val task1 = findViewById<CheckBox>(R.id.task_1)
        val task2 = findViewById<CheckBox>(R.id.task_2)
        val task3 = findViewById<CheckBox>(R.id.task_3)
        val task4 = findViewById<CheckBox>(R.id.task_4)
        val task5 = findViewById<CheckBox>(R.id.task_5)
        val progressText = findViewById<TextView>(R.id.progress_text)

        val checkboxes = listOf(task1, task2, task3, task4, task5)

        fun updateProgress() {
            val completed = checkboxes.count { it.isChecked }
            progressText.text = "$completed/5"
        }

        checkboxes.forEach { checkbox ->
            checkbox.setOnCheckedChangeListener { _, _ ->
                updateProgress()
            }
        }

        findViewById<android.widget.ImageView>(R.id.back_button).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<android.widget.Button>(R.id.done_button).setOnClickListener {
            val completed = checkboxes.count { it.isChecked }
            Toast.makeText(this, "Completed $completed of 5 tasks!", Toast.LENGTH_SHORT).show()
            finish()
        }

        updateProgress()
    }
}
