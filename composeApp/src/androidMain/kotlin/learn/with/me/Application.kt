package learn.with.me

import android.app.Application
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize
import learn.with.me.di.initializeKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidLogger()
            androidContext(this@Application)
        }

        val app = Firebase.initialize(context = this)
        println("FIREBASE APP $app")
    }
}