package com.simats.digitaldetox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView

class BlockedAttemptsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blocked_attempts)

        val btnClose = findViewById<ImageView>(R.id.btn_close)
        btnClose.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
