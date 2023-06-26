package com.example.quizproject.dataRepository

import android.util.Log
import com.example.quizproject.dataModel.AnswerModel
import com.example.quizproject.dataModel.BookModel
import com.example.quizproject.dataModel.Category
import com.example.quizproject.dataModel.ChapterModel
import com.example.quizproject.dataModel.CourseModel
import com.example.quizproject.dataModel.QuestionSet
//import com.example.quizproject.dataModel.CourseModel
//import com.example.quizproject.dataModel.CourseModel
import io.realm.kotlin.ext.query
import org.mongodb.kbson.ObjectId
import java.util.concurrent.Flow
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.BsonObjectId

//
interface MongoRepository {

    fun getCategoryData() : kotlinx.coroutines.flow.Flow<List<Category>>

    suspend fun insertCategory ( category: Category )

    suspend fun updateCategory( category: Category , courseName: String )

    fun getCourseData() : kotlinx.coroutines.flow.Flow<List<CourseModel>>

    fun getCourseDataById() : Category

    suspend fun insertCourse ( courseModel : CourseModel)

    suspend fun updateCourse ( courseModel: CourseModel,  bookName : String )


    fun getBookData() : kotlinx.coroutines.flow.Flow<List<BookModel>>

    suspend fun insertBook ( bookModel: BookModel )

    suspend fun updateBook ( bookModel: BookModel,  bookName : String )

    fun getChapterData() : kotlinx.coroutines.flow.Flow<List<ChapterModel>>

    suspend fun insertChapter ( chapterModel: ChapterModel )


    fun getAnswerData() : kotlinx.coroutines.flow.Flow<List<AnswerModel>>

    suspend fun insertAnswer ( answerModel: AnswerModel)


    fun getQuestionSetData() : kotlinx.coroutines.flow.Flow<List<QuestionSet>>

    suspend fun insertQuestionSet ( questionSet: QuestionSet )


}