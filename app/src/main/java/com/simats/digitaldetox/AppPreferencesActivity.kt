package com.simats.digitaldetox

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar

class AppPreferencesActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_preferences)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        sharedPreferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

        // Notifications
        setupSwitch(R.id.push_notifications_switch, "push_notifications", true)
        setupSwitch(R.id.daily_reminders_switch, "daily_reminders", true)
        setupSwitch(R.id.goal_reminders_switch, "goal_reminders", true)
        setupSwitch(R.id.motivational_quotes_switch, "motivational_quotes", false)
        setupSwitch(R.id.weekly_report_switch, "weekly_report", true)

        // Sound & Haptics
        setupSwitch(R.id.sound_effects_switch, "sound_effects", true)
        setupSwitch(R.id.vibration_switch, "vibration", true)

        // Focus Mode
        setupSwitch(R.id.auto_start_focus_switch, "auto_start_focus", false)
        setupSwitch(R.id.strict_mode_switch, "strict_mode", true)

        // Privacy & Security
        setupSwitch(R.id.biometric_lock_switch, "biometric_lock", false)
    }

    private fun setupSwitch(switchId: Int, prefKey: String, defaultValue: Boolean) {
        val switch = findViewById<SwitchCompat>(switchId)
        switch.isChecked = sharedPreferences.getBoolean(prefKey, defaultValue)
        switch.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean(prefKey, isChecked).apply()
        }
    }
}
