package com.simats.digitaldetox

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView

class SelectAlertTypeActivity : AppCompatActivity() {

    private var selectedCard: CardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_alert_type)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val timeBasedAlertCard = findViewById<CardView>(R.id.time_based_alert_card)
        val scheduleBasedAlertCard = findViewById<CardView>(R.id.schedule_based_alert_card)
        val appSpecificAlertCard = findViewById<CardView>(R.id.app_specific_alert_card)
        val goalBasedAlertCard = findViewById<CardView>(R.id.goal_based_alert_card)
        val customAlertCard = findViewById<CardView>(R.id.custom_alert_card)
        val selectButton = findViewById<Button>(R.id.select_button)

        val alertCards = listOf(
            timeBasedAlertCard,
            scheduleBasedAlertCard,
            appSpecificAlertCard,
            goalBasedAlertCard,
            customAlertCard
        )

        alertCards.forEach { card ->
            card.setOnClickListener {
                selectedCard?.isSelected = false
                card.isSelected = true
                selectedCard = card
            }
        }

        selectButton.setOnClickListener {
            if (selectedCard != null) {
                val intent = Intent(this, VoiceAlertsActivity::class.java)
                // You can pass data back to the VoiceAlertsActivity here if needed
                startActivity(intent)
                finish()
            } else {
                // Handle case where no card is selected
            }
        }
    }
}
