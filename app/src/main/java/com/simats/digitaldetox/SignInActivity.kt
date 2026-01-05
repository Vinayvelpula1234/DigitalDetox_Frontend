package com.simats.digitaldetox

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.simats.digitaldetox.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val emailInput = findViewById<EditText>(R.id.email_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val signInButton = findViewById<Button>(R.id.sign_in_button)
        val signUpText = findViewById<TextView>(R.id.sign_up_text)

        signInButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and password are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            RetrofitClient.instance.login(email, password)
                .enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            if (loginResponse?.status == "success" && loginResponse.user != null) {
                                Toast.makeText(this@SignInActivity, loginResponse.message, Toast.LENGTH_SHORT).show()

                                // Save user info to SharedPreferences
                                val sharedPref = getSharedPreferences("DigitalDetoxPrefs", Context.MODE_PRIVATE)
                                with(sharedPref.edit()) {
                                    putInt("USER_ID", loginResponse.user.id)
                                    putString("USER_NAME", loginResponse.user.name)
                                    putString("USER_EMAIL", email)
                                    apply()
                                }

                                val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@SignInActivity, loginResponse?.message ?: "Login failed", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            val errorBody = response.errorBody()?.string()
                            android.util.Log.e("DetoxDebug", "Login error: ${response.code()} - $errorBody")
                            Toast.makeText(this@SignInActivity, "Server error: ${response.code()}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        android.util.Log.e("DetoxDebug", "Network failure", t)
                        Toast.makeText(this@SignInActivity, "Network error: ${t.localizedMessage}", Toast.LENGTH_LONG).show()
                    }
                })
        }

        signUpText.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.forgot_password_text).setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
    }
}
