package com.simats.digitaldetox

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener { onBackPressed() }

        val sharedPref = getSharedPreferences("DigitalDetoxPrefs", Context.MODE_PRIVATE)
        val userName = sharedPref.getString("USER_NAME", "User")
        val userEmail = sharedPref.getString("USER_EMAIL", "user@example.com")

        val userNameTextView = findViewById<TextView>(R.id.user_name)
        val userEmailTextView = findViewById<TextView>(R.id.user_email)

        userNameTextView.text = userName
        userEmailTextView.text = userEmail

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.navigation_profile
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_profile -> {
                    // Already on the profile screen
                    true
                }
                else -> false
            }
        }
    }
}
