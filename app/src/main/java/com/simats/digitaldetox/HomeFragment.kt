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
import com.simats.digitaldetox.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val sharedPref = activity?.getSharedPreferences("DigitalDetoxPrefs", Context.MODE_PRIVATE)
        val userName = sharedPref?.getString("USER_NAME", "User")
        val userId = sharedPref?.getInt("USER_ID", -1)

        val userNameTextView = view.findViewById<TextView>(R.id.user_name)
        val userInitialTextView = view.findViewById<TextView>(R.id.user_initial)
        val streakValueTextView = view.findViewById<TextView>(R.id.streak_value)
        val pointsEarnedTextView = view.findViewById<TextView>(R.id.points_earned)
        val streakCard = view.findViewById<CardView>(R.id.streak_card)
        val focusModeCard = view.findViewById<CardView>(R.id.focus_mode_card)
        val voiceAlertsCard = view.findViewById<CardView>(R.id.voice_alerts_card)

        // Set user name
        if (!userName.isNullOrEmpty()) {
            userNameTextView.text = userName
            userInitialTextView.text = userName.first().toString()
        }

        // Fetch rewards data from database
        if (userId != null && userId != -1) {
            RetrofitClient.instance.getRewards(userId).enqueue(object : Callback<Rewards> {
                override fun onResponse(call: Call<Rewards>, response: Response<Rewards>) {
                    if (response.isSuccessful) {
                        val rewards = response.body()
                        if (rewards != null) {
                            streakValueTextView.text = "${rewards.streak_days} Days 🔥"
                            pointsEarnedTextView.text = "${rewards.points} Points Earned"
                        }
                    }
                }

                override fun onFailure(call: Call<Rewards>, t: Throwable) {
                    // Keep default values if fetch fails
                }
            })
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

        view.findViewById<CardView>(R.id.daily_challenges_card).setOnClickListener {
            startActivity(Intent(activity, DailyChallengesActivity::class.java))
        }

        view.findViewById<CardView>(R.id.app_blocker_card).setOnClickListener {
            startActivity(Intent(activity, AppBlockingActivity::class.java))
        }

        view.findViewById<android.widget.Button>(R.id.continue_plan_button).setOnClickListener {
            startActivity(Intent(activity, DetoxPlansActivity::class.java))
        }

        val rewardsIntent = Intent(activity, RewardsActivity::class.java)
        view.findViewById<CardView>(R.id.your_rewards_card).setOnClickListener {
            startActivity(rewardsIntent)
        }
        view.findViewById<android.widget.Button>(R.id.view_rewards_button).setOnClickListener {
            startActivity(rewardsIntent)
        }

        view.findViewById<CardView>(R.id.habit_roadmap_card).setOnClickListener {
            startActivity(Intent(activity, HabitRoadmapActivity::class.java))
        }

        view.findViewById<CardView>(R.id.daily_checklist_card).setOnClickListener {
            startActivity(Intent(activity, DailyChecklistActivity::class.java))
        }

        return view
    }
}
