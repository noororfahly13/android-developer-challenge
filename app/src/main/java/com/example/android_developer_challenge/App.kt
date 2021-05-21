package com.example.android_developer_challenge

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.android_developer_challenge.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.KoinExperimentalAPI
import org.koin.core.context.startKoin

class App : Application(), LifecycleObserver {
    @KoinExperimentalAPI
    override fun onCreate() {
        super.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        startKoin {
            androidContext(this@App)
            fragmentFactory()
            modules(
                listOf(
                    networkModule,
                    persistenceModule,
                    repositoryModule,
                    domainModule,
                    viewModelModule,
                    fragmentModule
                )
            )
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onAppBackgrounded() {
    }
}