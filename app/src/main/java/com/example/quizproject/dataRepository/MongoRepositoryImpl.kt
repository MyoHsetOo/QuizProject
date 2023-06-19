package com.example.quizproject.dataRepository

import com.example.quizproject.dataModel.Category
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MongoRepositoryImpl ( val realm: Realm) : MongoRepository {

    override fun getCategoryData(): Flow<List<Category>> {
        return realm.query<Category>().asFlow().map { it.list }
    }

    override suspend fun insertCategory( category: Category) {
        realm.write { copyToRealm(category) }
    }

}