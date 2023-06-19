package com.example.quizproject.dataRepository

import com.example.quizproject.dataModel.Category
import java.util.concurrent.Flow

interface MongoRepository {

    fun getCategoryData() : kotlinx.coroutines.flow.Flow<List<Category>>

    suspend fun insertCategory ( category: Category )
}