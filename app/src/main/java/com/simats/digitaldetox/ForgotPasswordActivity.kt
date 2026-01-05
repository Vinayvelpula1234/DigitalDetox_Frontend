package com.simats.digitaldetox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        findViewById<android.widget.ImageView>(R.id.back_button).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<android.widget.Button>(R.id.send_code_button).setOnClickListener {
            // TODO: Implement password reset email logic
            android.widget.Toast.makeText(this, "Verification code sent!", android.widget.Toast.LENGTH_SHORT).show()
            startActivity(android.content.Intent(this, VerifyCodeActivity::class.java))
        }

        findViewById<android.widget.TextView>(R.id.back_to_sign_in).setOnClickListener {
            finish()
        }
    }
}
