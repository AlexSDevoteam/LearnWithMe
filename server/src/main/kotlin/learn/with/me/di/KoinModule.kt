package learn.with.me.di

import learn.with.me.repository.LessonRepository
import learn.with.me.repository.LessonRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
    single<LessonRepository> { LessonRepositoryImpl() }
}