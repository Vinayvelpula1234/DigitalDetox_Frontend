package com.simats.digitaldetox

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        val fullNameInput = findViewById<EditText>(R.id.full_name_input)
        val emailInput = findViewById<EditText>(R.id.email_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val signUpButton = findViewById<Button>(R.id.sign_up_button)
        val signInText = findViewById<TextView>(R.id.sign_in_text)

        signUpButton.setOnClickListener {
            val name = fullNameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            RetrofitClient.instance.register(name, email, password)
                .enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            if (loginResponse?.status == "success" && loginResponse.user != null) {
                                Toast.makeText(this@CreateAccountActivity, loginResponse.message, Toast.LENGTH_SHORT).show()

                                // Save user info to SharedPreferences
                                val sharedPref = getSharedPreferences("DigitalDetoxPrefs", Context.MODE_PRIVATE)
                                with(sharedPref.edit()) {
                                    putInt("USER_ID", loginResponse.user.id)
                                    putString("USER_NAME", loginResponse.user.name)
                                    putString("USER_EMAIL", email)
                                    apply()
                                }

                                val intent = Intent(this@CreateAccountActivity, HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@CreateAccountActivity, loginResponse?.message ?: "Registration failed", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this@CreateAccountActivity, "Registration failed", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@CreateAccountActivity, "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        }

        signInText.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
