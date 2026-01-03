package com.simats.digitaldetox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val analyticsFragment = AnalyticsFragment()
    private val remindersFragment = RemindersFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Set default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment).commit()
        }

        bottomNavigation.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.navigation_home -> selectedFragment = homeFragment
                R.id.navigation_analytics -> selectedFragment = analyticsFragment
                R.id.navigation_reminders -> selectedFragment = remindersFragment
                R.id.navigation_profile -> selectedFragment = profileFragment
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
            }
            true
        }
    }
}
