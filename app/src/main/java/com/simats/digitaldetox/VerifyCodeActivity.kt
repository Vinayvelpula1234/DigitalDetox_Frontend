package com.simats.digitaldetox

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VerifyCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_code)

        val otp1 = findViewById<EditText>(R.id.otp_1)
        val otp2 = findViewById<EditText>(R.id.otp_2)
        val otp3 = findViewById<EditText>(R.id.otp_3)
        val otp4 = findViewById<EditText>(R.id.otp_4)

        // Auto-focus next field
        setupOtpInput(otp1, otp2)
        setupOtpInput(otp2, otp3)
        setupOtpInput(otp3, otp4)

        findViewById<android.widget.ImageView>(R.id.back_button).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<android.widget.Button>(R.id.verify_button).setOnClickListener {
            val code = "${otp1.text}${otp2.text}${otp3.text}${otp4.text}"
            if (code.length == 4) {
                // TODO: Verify code with backend
                startActivity(Intent(this, ResetPasswordActivity::class.java))
            } else {
                Toast.makeText(this, "Please enter complete code", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<android.widget.TextView>(R.id.resend_code).setOnClickListener {
            Toast.makeText(this, "Code resent!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupOtpInput(current: EditText, next: EditText?) {
        current.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    next?.requestFocus()
                }
            }
        })
    }
}
