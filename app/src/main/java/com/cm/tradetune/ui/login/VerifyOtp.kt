package com.cm.tradetune.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cm.tradetune.R
import com.cm.tradetune.databinding.ActivityVerifyOtpBinding
import com.cm.tradetune.ui.home.DashBoard

class VerifyOtp : AppCompatActivity() {
    private lateinit var binding: ActivityVerifyOtpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListeners()
        // Add other Verify OTP related logic as needed
    }

    private fun setClickListeners() {
        binding.editNumberButton.setOnClickListener {
            // Handle the logic to navigate back to the Login screen
            launchLoginScreen()
        }

        binding.verifyOtpButton.setOnClickListener{
            launchHomeScreen()
        }

        // Add other click listeners as needed
    }

    private fun launchHomeScreen() {
        val intent = Intent(this, DashBoard::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Optional: Close the current Verify OTP screen
    }

    private fun launchLoginScreen() {
        val intent = Intent(this, Login::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Optional: Close the current Verify OTP screen
    }
}