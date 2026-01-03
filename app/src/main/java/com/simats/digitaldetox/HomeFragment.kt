package com.simats.digitaldetox

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val sharedPref = activity?.getSharedPreferences("DigitalDetoxPrefs", Context.MODE_PRIVATE)
        val userName = sharedPref?.getString("USER_NAME", "User")

        val userNameTextView = view.findViewById<TextView>(R.id.user_name)
        val userInitialTextView = view.findViewById<TextView>(R.id.user_initial)
        val streakCard = view.findViewById<CardView>(R.id.streak_card)
        val focusModeCard = view.findViewById<CardView>(R.id.focus_mode_card)
        val voiceAlertsCard = view.findViewById<CardView>(R.id.voice_alerts_card)

        if (!userName.isNullOrEmpty()) {
            userNameTextView.text = userName
            userInitialTextView.text = userName.first().toString()
        }

        streakCard.setOnClickListener {
            val intent = Intent(activity, StreakProgressActivity::class.java)
            startActivity(intent)
        }

        focusModeCard.setOnClickListener {
            val intent = Intent(activity, FocusSessionActivity::class.java)
            startActivity(intent)
        }

        voiceAlertsCard.setOnClickListener {
            val intent = Intent(activity, VoiceAlertsActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
