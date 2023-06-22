package com.example.quizproject.dataRepository

import android.util.Log
import com.example.quizproject.dataModel.Category
import com.example.quizproject.dataModel.CourseModel
//import com.example.quizproject.dataModel.CourseModel
//import com.example.quizproject.dataModel.CourseModel
import io.realm.kotlin.ext.query
import org.mongodb.kbson.ObjectId
import java.util.concurrent.Flow
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.map
//
interface MongoRepository {

    fun getCategoryData() : kotlinx.coroutines.flow.Flow<List<Category>>

    suspend fun insertCategory ( category: Category )

    fun getCourseData() : kotlinx.coroutines.flow.Flow<List<CourseModel>>

    suspend fun insertCourse ( courseModel : CourseModel)




}