package com.simats.digitaldetox

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class VoiceAlertsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_alerts)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val createNewAlertButton = findViewById<Button>(R.id.create_new_alert_button)
        createNewAlertButton.setOnClickListener {
            val intent = Intent(this, SelectAlertTypeActivity::class.java)
            startActivity(intent)
        }
    }
}
