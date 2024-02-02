package com.cm.tradetune.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cm.tradetune.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListeners()
        // Add other login-related logic as needed
    }

    private fun setClickListeners() {
        binding.signUpText.setOnClickListener {
            navigateToSignUp()
        }

        binding.getOtpButton.setOnClickListener {
            // Handle the logic to initiate OTP generation and verification
            launchVerifyOtpScreen()
        }

        // Add other click listeners as needed
    }

    private fun launchVerifyOtpScreen() {
        val intent = Intent(this, VerifyOtp::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
    private fun navigateToSignUp() {
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(Intent(this, Register::class.java))
        finish()
    }
}