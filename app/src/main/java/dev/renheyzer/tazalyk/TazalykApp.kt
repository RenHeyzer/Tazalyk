package dev.renheyzer.tazalyk

import android.app.Application
import dev.renheyzer.tazalyk.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TazalykApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TazalykApp)
            modules(appModule)
        }
    }
}