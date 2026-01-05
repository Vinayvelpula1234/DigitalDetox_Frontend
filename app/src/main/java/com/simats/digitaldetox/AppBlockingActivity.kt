package com.simats.digitaldetox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class AppBlockingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_blocking)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        findViewById<androidx.cardview.widget.CardView>(R.id.card_block_apps).setOnClickListener {
            startActivity(android.content.Intent(this, BlockAppsListActivity::class.java))
        }

        findViewById<androidx.cardview.widget.CardView>(R.id.card_block_websites).setOnClickListener {
            startActivity(android.content.Intent(this, BlockedWebsitesActivity::class.java))
        }

        findViewById<android.widget.TextView>(R.id.tv_view_all_active_blocks).setOnClickListener {
            startActivity(android.content.Intent(this, ActiveBlocksActivity::class.java))
        }

        findViewById<androidx.cardview.widget.CardView>(R.id.card_block_by_category).setOnClickListener {
            startActivity(android.content.Intent(this, BlockByCategoryActivity::class.java))
        }

        findViewById<androidx.cardview.widget.CardView>(R.id.card_view_blocked_attempts).setOnClickListener {
            startActivity(android.content.Intent(this, BlockedAttemptsActivity::class.java))
        }
    }
}
