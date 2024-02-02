package com.cm.tradetune.di

import com.cm.tradetune.ui.feed.CreateFeed
import com.cm.tradetune.ui.feed.CreateFeedViewModel
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(viewModel: CreateFeed)

}
