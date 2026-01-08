package learn.with.me.di


import learn.with.me.auth.data.auth.AuthRepositoryImpl
import learn.with.me.auth.domain.auth.AuthRepository
import learn.with.me.auth.presentation.SharedAuthViewModel
import learn.with.me.auth.presentation.login.LoginViewModel
import learn.with.me.auth.presentation.register.RegisterViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    single<AuthRepository> { AuthRepositoryImpl() }
    viewModelOf(::SharedAuthViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}

fun initializeKoin(
    config: (KoinApplication.() -> Unit)? = null,
) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule)
    }
}