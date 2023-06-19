package com.example.quizproject.di

import com.example.quizproject.dataModel.Category
import com.example.quizproject.dataRepository.MongoRepository
import com.example.quizproject.dataRepository.MongoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                Category::class,
            )
        )
            .compactOnLaunch()
            .build()
        return Realm.open(config)
    }

    @Singleton
    @Provides
    fun provideMongoRepository( realm: Realm ): MongoRepository {
        return MongoRepositoryImpl(realm = realm)
    }
}