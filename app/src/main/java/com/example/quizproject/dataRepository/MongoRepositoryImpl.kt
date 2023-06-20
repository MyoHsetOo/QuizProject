package com.example.quizproject.dataRepository

import com.example.quizproject.app
import com.example.quizproject.dataModel.Category
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.exceptions.SyncException
import io.realm.kotlin.mongodb.subscriptions
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import io.realm.kotlin.mongodb.sync.SyncSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

 class MongoRepositoryImpl () : MongoRepository {

    private val realm: Realm
    private val config: SyncConfiguration
    private val currentUser: User
        get() = app.currentUser!!

    init {
        config = SyncConfiguration.Builder(currentUser, setOf(Category::class))
            .initialSubscriptions { realm ->
                // Subscribe to the active subscriptionType - first time defaults to MINE
                add(
                    realm.query<Category>(
                       /* "categoryName == ",*/

                    ),
                    "subscription name"
                )
            }

            .waitForInitialRemoteData()
            .build()

        realm = Realm.open(config)

        // Mutable states must be updated on the UI thread
        CoroutineScope(Dispatchers.Main).launch {
            realm.subscriptions.waitForSynchronization()
        }
    }


    override fun getCategoryData(): Flow<List<Category>> {
        return realm.query<Category>().asFlow().map { it.list }
    }

    override suspend fun insertCategory( category: Category) {
        realm.write { copyToRealm(category) }
    }



}