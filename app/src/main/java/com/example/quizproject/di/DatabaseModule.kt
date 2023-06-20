package com.example.quizproject.di

import com.example.quizproject.dataModel.Category
import com.example.quizproject.dataRepository.MongoRepository
import com.example.quizproject.dataRepository.MongoRepositoryImpl

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/*
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

    */
/*fun provideRealm(): Realm {

        val app = App.Companion.create("quizz-uacma")

        runBlocking {
            val user = app.login(Credentials.anonymous())

            val config = SyncConfiguration.Builder(user, setOf(Category::class))
                .name("realm name")
                .initialSubscriptions { realm ->
                    add(
                        realm.query<Category>(
                           "categoryName == $0",
                            "name value"
                        ),
                        "subscription name"
                    )
                }
                .build()
            return  Realm.open(config)
        }

    }*//*



    @Singleton
    @Provides
    fun provideMongoRepository( realm: Realm ): MongoRepository {
        return MongoRepositoryImpl(realm = realm)
    }
}*/
