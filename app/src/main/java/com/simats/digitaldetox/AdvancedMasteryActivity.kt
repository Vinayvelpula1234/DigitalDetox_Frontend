package com.simats.digitaldetox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AdvancedMasteryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advanced_mastery)

        findViewById<android.view.View>(R.id.back_button).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
