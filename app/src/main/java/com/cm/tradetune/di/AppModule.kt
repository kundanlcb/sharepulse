package com.cm.tradetune.di

import android.app.Application
import com.cm.tradetune.webservice.ApiService
import com.cm.tradetune.webservice.EquityRepository
import com.cm.tradetune.webservice.UserRepository
import com.cm.tradetune.webservice.WebService
import com.cm.tradetune.ui.feed.CreateFeedViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {
    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://192.168.107.124:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideWebService(): WebService {
        // Provide your WebService implementation here
        return WebService()
    }


    @Provides
    fun provideUserRepository(apiService: ApiService, webService: WebService): UserRepository {
        return UserRepository(webService, apiService)
    }


    @Provides
    fun provideEquityRepository(apiService: ApiService, webService: WebService): EquityRepository {
        return EquityRepository(webService, apiService)
    }

    @Provides
    fun provideCreateFeedViewModel(
        application: Application,
        userRepository: UserRepository, // Replace with your actual UserRepository
        equityRepository: EquityRepository // Replace with your actual EquityRepository
    ): CreateFeedViewModel {
        return CreateFeedViewModel(application, userRepository, equityRepository)
    }


    @Component(modules = [AppModule::class])
    interface AppComponent {
        fun inject(createFeedViewModel: CreateFeedViewModel)
    }
}
