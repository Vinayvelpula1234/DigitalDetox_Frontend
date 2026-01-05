package com.simats.digitaldetox

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        val newPassword = findViewById<android.widget.EditText>(R.id.new_password_input)
        val confirmPassword = findViewById<android.widget.EditText>(R.id.confirm_password_input)

        findViewById<android.widget.ImageView>(R.id.back_button).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<android.widget.Button>(R.id.reset_password_button).setOnClickListener {
            val newPass = newPassword.text.toString()
            val confirmPass = confirmPassword.text.toString()

            when {
                newPass.isEmpty() -> Toast.makeText(this, "Enter new password", Toast.LENGTH_SHORT).show()
                confirmPass.isEmpty() -> Toast.makeText(this, "Confirm password", Toast.LENGTH_SHORT).show()
                newPass != confirmPass -> Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
                newPass.length < 6 -> Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                else -> {
                    // TODO: Update password via backend
                    startActivity(Intent(this, PasswordResetSuccessActivity::class.java))
                    finish()
                }
            }
        }
    }
}
