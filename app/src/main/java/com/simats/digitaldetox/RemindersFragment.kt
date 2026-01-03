package com.simats.digitaldetox

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class RemindersFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reminders, container, false)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            (activity as? HomeActivity)?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.selectedItemId = R.id.navigation_home
        }

        sharedPreferences = requireActivity().getSharedPreferences("RemindersPrefs", Context.MODE_PRIVATE)

        // All Reminders
        setupSwitch(view, R.id.daily_goal_reminder_switch, "daily_goal_reminder", true)
        setupSwitch(view, R.id.focus_session_switch, "focus_session", true)
        setupSwitch(view, R.id.take_a_break_switch, "take_a_break", true)
        setupSwitch(view, R.id.bedtime_reminder_switch, "bedtime_reminder", true)
        setupSwitch(view, R.id.evening_detox_switch, "evening_detox", false)
        setupSwitch(view, R.id.usage_alert_switch, "usage_alert", true)

        // Notification Preferences
        setupSwitch(view, R.id.sound_switch, "sound", true)
        setupSwitch(view, R.id.vibration_switch, "vibration", true)
        setupSwitch(view, R.id.do_not_disturb_switch, "do_not_disturb", false)

        return view
    }

    private fun setupSwitch(view: View, switchId: Int, prefKey: String, defaultValue: Boolean) {
        val switch = view.findViewById<SwitchCompat>(switchId)
        switch.isChecked = sharedPreferences.getBoolean(prefKey, defaultValue)
        switch.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean(prefKey, isChecked).apply()
        }
    }
}
