package com.simats.digitaldetox

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.simats.digitaldetox.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val sharedPref = activity?.getSharedPreferences("DigitalDetoxPrefs", Context.MODE_PRIVATE)
        val userId = sharedPref?.getInt("USER_ID", -1)

        val userNameTextView = view.findViewById<TextView>(R.id.user_name)
        val userEmailTextView = view.findViewById<TextView>(R.id.user_email)
        val logoutButton = view.findViewById<Button>(R.id.logout_button)
        val editProfileButton = view.findViewById<Button>(R.id.edit_profile_button)
        val appPreferencesButton = view.findViewById<Button>(R.id.app_preferences_button)

        if (userId != -1 && userId != null) {
            RetrofitClient.instance.getProfile(userId).enqueue(object : Callback<Profile> {
                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                    if (response.isSuccessful) {
                        val profile = response.body()
                        if (profile != null) {
                            userNameTextView.text = profile.name
                            userEmailTextView.text = profile.email
                        }
                    }
                }

                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    Toast.makeText(activity, "Failed to load profile", Toast.LENGTH_SHORT).show()
                }
            })
        }

        logoutButton.setOnClickListener {
            // Clear SharedPreferences
            val editor = sharedPref?.edit()
            editor?.clear()
            editor?.apply()

            // Navigate to SignInActivity
            val intent = Intent(activity, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        editProfileButton.setOnClickListener {
            val intent = Intent(activity, EditProfileActivity::class.java)
            startActivity(intent)
        }

        appPreferencesButton.setOnClickListener {
            val intent = Intent(activity, AppPreferencesActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
