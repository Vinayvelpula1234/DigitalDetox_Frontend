package com.simats.digitaldetox

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileActivity : AppCompatActivity() {

    private lateinit var fullNameInput: TextInputEditText
    private lateinit var emailInput: TextInputEditText
    private lateinit var languageInput: TextInputEditText // Assuming you add this to your layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressed() }

        fullNameInput = findViewById(R.id.full_name_input)
        emailInput = findViewById(R.id.email_input)
        // languageInput = findViewById(R.id.language_input) // Make sure to add this to your XML

        val saveChangesButton = findViewById<Button>(R.id.save_changes_button)
        val cancelButton = findViewById<Button>(R.id.cancel_button)

        val sharedPref = getSharedPreferences("DigitalDetoxPrefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getInt("USER_ID", -1)

        if (userId != -1) {
            RetrofitClient.instance.getProfile(userId).enqueue(object : Callback<Profile> {
                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                    if (response.isSuccessful) {
                        val profile = response.body()
                        if (profile != null) {
                            fullNameInput.setText(profile.name)
                            emailInput.setText(profile.email)
                            // languageInput.setText(profile.language)
                        }
                    }
                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    Toast.makeText(this@EditProfileActivity, "Failed to load profile", Toast.LENGTH_SHORT).show()
                }
            })
        }

        saveChangesButton.setOnClickListener {
            val name = fullNameInput.text.toString().trim()
            // val language = languageInput.text.toString().trim()

            if (name.isEmpty()) { // Add language to this check if it's required
                Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (userId != -1) {
                RetrofitClient.instance.updateProfile(userId, name, "EN").enqueue(object : Callback<UpdateProfileResponse> {
                    override fun onResponse(call: Call<UpdateProfileResponse>, response: Response<UpdateProfileResponse>) {
                        if (response.isSuccessful) {
                            val updateResponse = response.body()
                            if (updateResponse?.status == "profile_updated") {
                                Toast.makeText(this@EditProfileActivity, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                                finish()
                            } else {
                                Toast.makeText(this@EditProfileActivity, "Failed to update profile", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<UpdateProfileResponse>, t: Throwable) {
                        Toast.makeText(this@EditProfileActivity, "An error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}
