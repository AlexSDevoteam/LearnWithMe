package learn.with.me.androidApp

import com.google.firebase.Firebase
import com.google.firebase.initialize
import learn.with.me.di.initializeKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class Application : android.app.Application() {
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