package com.simats.digitaldetox

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class PermissionsActivity : AppCompatActivity() {

    private var enabledPermissions = 6
    private val totalPermissions = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        val requiredPermissionsTitle = findViewById<TextView>(R.id.required_permissions_title)
        val continueButton = findViewById<Button>(R.id.continue_button)

        val screenTimeSwitch = findViewById<SwitchCompat>(R.id.screen_time_switch)
        val appUsageSwitch = findViewById<SwitchCompat>(R.id.app_usage_switch)
        val notificationsSwitch = findViewById<SwitchCompat>(R.id.notifications_switch)
        val focusModeSwitch = findViewById<SwitchCompat>(R.id.focus_mode_switch)
        val backgroundActivitySwitch = findViewById<SwitchCompat>(R.id.background_activity_switch)
        val dndSwitch = findViewById<SwitchCompat>(R.id.dnd_switch)

        val switches = listOf(
            screenTimeSwitch,
            appUsageSwitch,
            notificationsSwitch,
            focusModeSwitch,
            backgroundActivitySwitch,
            dndSwitch
        )

        fun updatePermissionsTitle() {
            var count = 0
            switches.forEach { if(it.isChecked) count++ }
            enabledPermissions = count
            requiredPermissionsTitle.text = "⭐ Required Permissions ($enabledPermissions/$totalPermissions)"
        }

        switches.forEach { switch ->
            switch.setOnCheckedChangeListener { _, _ ->
                updatePermissionsTitle()
            }
        }

        continueButton.setOnClickListener {
            if (enabledPermissions == totalPermissions) {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("USER_NAME", getIntent().getStringExtra("USER_NAME"))
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please enable all required permissions", Toast.LENGTH_SHORT).show()
            }
        }

        updatePermissionsTitle()
    }
}
