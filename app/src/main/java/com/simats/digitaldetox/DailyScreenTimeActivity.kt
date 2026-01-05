package com.simats.digitaldetox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DailyScreenTimeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_screen_time)

        findViewById<android.view.View>(R.id.back_button).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<android.widget.TextView>(R.id.tv_view_all_apps).setOnClickListener {
            startActivity(android.content.Intent(this, AppWiseUsageDetailActivity::class.java))
        }

        findViewById<android.widget.TextView>(R.id.tv_daily_report_title).setOnClickListener {
            startActivity(android.content.Intent(this, AppWiseUsageDetailActivity::class.java))
        }
    }
}
