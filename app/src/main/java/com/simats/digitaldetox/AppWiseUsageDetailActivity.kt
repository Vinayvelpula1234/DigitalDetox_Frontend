package com.simats.digitaldetox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class AppWiseUsageDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_wise_usage_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        findViewById<android.widget.TextView>(R.id.tab_this_week).setOnClickListener {
            val intent = android.content.Intent(this, AppWiseUsageActivity::class.java)
            intent.addFlags(android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish() // Optional: Finish current to avoid stack pileup if toggling frequently
        }
    }
}
