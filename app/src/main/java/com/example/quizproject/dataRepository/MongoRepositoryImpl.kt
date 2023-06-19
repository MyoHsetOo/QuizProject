package com.example.quizproject.dataRepository

import android.util.Log
import com.example.quizproject.dataModel.Category
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId

class MongoRepositoryImpl ( val realm: Realm) : MongoRepository {

    override fun getCategoryData(): Flow<List<Category>> {
        return realm.query<Category>().asFlow().map { it.list }
    }

    override suspend fun insertCategory( category: Category) {
        realm.write { copyToRealm(category) }
    }

    override suspend fun deleteCategory(id: ObjectId) {
        realm.write {
            val category = query<Category>(query = "categoryId == $0", id).first().find()
            try {
                category?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("MongoRepositoryImpl", "${e.message}")
            }
        }
    }

}