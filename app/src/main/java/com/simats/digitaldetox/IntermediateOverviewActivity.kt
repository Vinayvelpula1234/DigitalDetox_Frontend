package com.simats.digitaldetox

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class IntermediateOverviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intermediate_overview)

        findViewById<android.view.View>(R.id.back_button).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<android.widget.TextView>(R.id.tab_completed).setOnClickListener {
            val intent = Intent(this, IntermediateCompletedActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
    }
}
