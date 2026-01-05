package com.simats.digitaldetox

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PasswordResetSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset_success)

        findViewById<android.widget.Button>(R.id.back_to_sign_in_button).setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
