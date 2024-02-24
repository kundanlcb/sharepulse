package com.cm.tradetune.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cm.tradetune.R
import com.cm.tradetune.databinding.ActivityOnBoardingBinding
import com.cm.tradetune.databinding.ActivitySearchBinding
import com.cm.tradetune.ui.search.SearchViewModel
import com.cm.tradetune.ui.search.indice.IndicesAdapter
import com.cm.tradetune.ui.search.user.UserListAdapter

class OnBoarding : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var userAdapter: UserListAdapter
    private lateinit var equityAdapter: IndicesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
    }
}