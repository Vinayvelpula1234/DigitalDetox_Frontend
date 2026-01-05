package com.simats.digitaldetox

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class DetoxPlansActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detox_plans)

        findViewById<android.widget.ImageView>(R.id.back_button).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<CardView>(R.id.beginner_plan_card).setOnClickListener {
            val intent = Intent(this, DetoxPlanDetailActivity::class.java)
            intent.putExtra("plan_type", "beginner")
            startActivity(intent)
        }

        findViewById<CardView>(R.id.intermediate_plan_card).setOnClickListener {
            startActivity(Intent(this, IntermediateOverviewActivity::class.java))
        }

        findViewById<CardView>(R.id.advanced_plan_card).setOnClickListener {
            val intent = Intent(this, DetoxPlanDetailActivity::class.java)
            intent.putExtra("plan_type", "advanced")
            startActivity(intent)
        }
    }
}
