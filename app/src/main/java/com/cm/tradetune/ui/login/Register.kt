package com.cm.tradetune.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cm.tradetune.R
import com.cm.tradetune.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListeners()
        // Add other sign-up-related logic as needed
    }

    private fun setClickListeners() {
        binding.loginLinkText.setOnClickListener {
            navigateToLogin()
        }

        // Add other click listeners as needed
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, Login::class.java))
    }
}