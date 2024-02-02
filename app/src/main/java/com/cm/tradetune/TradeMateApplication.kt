package com.cm.tradetune

import android.app.Application
import com.cm.tradetune.di.AppComponent
import com.cm.tradetune.di.AppModule
import com.cm.tradetune.di.DaggerAppComponent


class TradeMateApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }


}
