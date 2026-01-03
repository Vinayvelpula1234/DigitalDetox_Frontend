package com.simats.digitaldetox

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CountdownActivity : AppCompatActivity() {

    private lateinit var countdownText: TextView
    private lateinit var countdownProgress: ProgressBar
    private lateinit var sessionGoalText: TextView
    private lateinit var pausePlayButton: ImageButton
    private lateinit var endSessionButton: Button

    private var timer: CountDownTimer? = null
    private var timeLeftInMillis: Long = 0
    private var isTimerRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countdown)

        countdownText = findViewById(R.id.countdown_text)
        countdownProgress = findViewById(R.id.countdown_progress)
        sessionGoalText = findViewById(R.id.session_goal_text)
        pausePlayButton = findViewById(R.id.pause_play_button)
        endSessionButton = findViewById(R.id.end_session_button)

        val duration = intent.getIntExtra("DURATION", 25)
        val sessionGoal = intent.getStringExtra("SESSION_GOAL")

        sessionGoalText.text = sessionGoal
        timeLeftInMillis = (duration * 60 * 1000).toLong()

        countdownProgress.max = duration * 60

        startTimer()

        pausePlayButton.setOnClickListener {
            if (isTimerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        endSessionButton.setOnClickListener {
            endSession()
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                countdownText.text = String.format("%02d:%02d", minutes, seconds)
                countdownProgress.progress = (countdownProgress.max - (millisUntilFinished / 1000)).toInt()
            }

            override fun onFinish() {
                countdownText.text = "00:00"
                isTimerRunning = false
                Toast.makeText(this@CountdownActivity, "Focus Session Finished!", Toast.LENGTH_SHORT).show()
                // You might want to navigate back or show a summary screen here
            }
        }.start()

        isTimerRunning = true
        pausePlayButton.setImageResource(R.drawable.ic_pause)
    }

    private fun pauseTimer() {
        timer?.cancel()
        isTimerRunning = false
        pausePlayButton.setImageResource(R.drawable.ic_play_arrow)
    }

    private fun endSession() {
        timer?.cancel()
        isTimerRunning = false
        Toast.makeText(this, "Session Ended", Toast.LENGTH_SHORT).show()
        onBackPressed()
    }
}
