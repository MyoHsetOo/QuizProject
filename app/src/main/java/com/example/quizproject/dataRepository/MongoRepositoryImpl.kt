package com.example.quizproject.dataRepository

import android.util.AndroidException
import android.util.Log
import com.example.quizproject.app
import com.example.quizproject.dataModel.AnswerModel
import com.example.quizproject.dataModel.BookModel
import com.example.quizproject.dataModel.Category
import com.example.quizproject.dataModel.ChapterModel
import com.example.quizproject.dataModel.CourseModel
import com.example.quizproject.dataModel.QuestionSet
import com.example.quizproject.viewModel.CourseViewModel
//import com.example.quizproject.dataModel.CourseModel
//import com.example.quizproject.dataModel.CourseModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.exceptions.SyncException
import io.realm.kotlin.mongodb.subscriptions
import io.realm.kotlin.mongodb.sync.Subscription
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import io.realm.kotlin.mongodb.sync.SyncSession
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId
//
 class MongoRepositoryImpl () : MongoRepository {

    private val realm: Realm
    private val config: SyncConfiguration




    private val currentUser: User
        get() = app.currentUser!!

    init {
        config = SyncConfiguration.Builder(currentUser, setOf( Category::class ,CourseModel::class, BookModel::class,ChapterModel::class, AnswerModel::class, QuestionSet::class))
            .initialSubscriptions { realm ->

                Log.d("Realm>>>","$realm")
                // Subscribe to the active subscriptionType - first time defaults to MINE

                add(
                    realm.query<AnswerModel>(

                    ),
                    "AnswerSubscription name",
                )
                add(
                    realm.query<QuestionSet>(

                    ),
                    "QuestionSetSubscription name",
                )
                add(
                    realm.query<ChapterModel>(

                    ),
                    "chapterSubscription name",
                )


                add(
                    realm.query<BookModel>(

                    ),
                    "bookSubscription name",
                )
                add(
                    realm.query<CourseModel>(

                    ),
                    "courseSubscription name"
                )
                add(
                    realm.query<Category>(
                       /* "categoryName == ",*/
                    ),
                    "subscription name",
                )


            }
            .waitForInitialRemoteData()
            .build()


        realm = Realm.open(config)


        // Mutable states must be updated on the UI thread
        CoroutineScope(Dispatchers.Main).launch {
            realm.subscriptions.waitForSynchronization()
        }

        var viewModelCourse : CourseViewModel = CourseViewModel( this )

        var course = viewModelCourse._courseName
    }


    override fun getCategoryData(): Flow<List<Category>> {
        return realm.query<Category>().asFlow().map { it.list }
    }

    override suspend fun insertCategory( category: Category) {
        realm.write { copyToRealm(category) }
    }

    override suspend fun updateCategory( category: Category , name : String ) {

        realm.write {
            val queriedPerson =
                query<Category>(query = "_id == $0", category._id)
                    .first()
                    .find()
            if (queriedPerson != null) {

                queriedPerson.courses.add(  CourseModel().apply {
                    courseCategoryId = category._id.toString()
                    courseName = name
                })

            } else {
                Log.d("MongoRepository", "Queried Person does not exist.")
            }

           // category.courses.add( )
        }
    }

    override fun getCourseDataById(): Category {
        TODO("Not yet implemented")
    }



    override fun getCourseData() : Flow<List<CourseModel>> {
        return realm.query<CourseModel>().asFlow().map { it.list }
    }

    override suspend fun insertCourse( courseModel : CourseModel) {
        realm.write { copyToRealm( courseModel) }
    }


    override suspend fun updateCourse( courseModel: CourseModel , name : String ) {

        realm.write {
            val queriedPerson =
                query<CourseModel>(query = "_id == $0", courseModel._id)
                    .first()
                    .find()
            if (queriedPerson != null) {

                queriedPerson.books.add(  BookModel().apply {
                    bookCourseId = courseModel._id.toString()
                    bookName = name
                })

            } else {
                Log.d("MongoRepository", "Queried Person does not exist.")
            }

        }
    }

    override fun getBookData() : Flow<List<BookModel>> {
        return realm.query<BookModel>().asFlow().map { it.list }
    }

    override suspend fun insertBook( bookModel: BookModel) {
        realm.write { copyToRealm( bookModel) }
    }


    override fun getChapterData() : Flow<List<ChapterModel>> {
        return realm.query<ChapterModel>().asFlow().map { it.list }
    }

    override suspend fun insertChapter( chapterModel: ChapterModel) {
        realm.write { copyToRealm( chapterModel) }
    }

    override suspend fun updateChapter( chapterModel: ChapterModel ,
                                        question_No : String ,
                                        question_Type: String,
                                        question_Text : String,
                                        question_Image : String,
                                        answersSet : List<AnswerModel>,
                                        correct_Answer : String ,
                                        solution_Type : String,
                                        solution_Text : String ,
                                        solution_Image : String ) {

        realm.write {
            val queriedPerson =
                query<ChapterModel>(query = "_id == $0", chapterModel._id)
                    .first()
                    .find()
            if (queriedPerson != null) {

                queriedPerson.questions.add( QuestionSet().apply {



                    questionChapterId = chapterModel._id.toString()
                    questionNo = question_No
                    questionType = question_Type
                    questionText = question_Text
                    questionImage = question_Image
                    for ( item in answersSet ){
                        answers.add( item )
                    }
                    correctAnswerType = correct_Answer
                    solutionType = solution_Type
                    solutionText = solution_Text
                    solutionImage = solution_Image

                    Log.d("cHapter<<<<<","Success")


                })

                Log.d("cHapter<<<<<","${queriedPerson.questions.size}")

            } else {
                Log.d("MongoRepository", "Queried Person does not exist.")
            }

        }
    }


    override suspend fun updateBook( bookModel: BookModel , name : String ) {

        realm.write {
            val queriedPerson =
                query<BookModel>(query = "_id == $0", bookModel._id)
                    .first()
                    .find()
            if (queriedPerson != null) {

                queriedPerson.chapters.add(  ChapterModel().apply {
                    chapterBookId = bookModel._id.toString()
                    chapterName = name
                })

            } else {
                Log.d("MongoRepository", "Queried Person does not exist.")
            }

        }
    }


    override fun getAnswerData() : Flow<List<AnswerModel>> {
        return realm.query<AnswerModel>().asFlow().map { it.list }
    }

    override suspend fun insertAnswer( answerModel: AnswerModel ) {
        realm.write { copyToRealm( answerModel) }
    }


    override  fun getQuestionSetData() : Flow<List<QuestionSet>> {
        return realm.query<QuestionSet>().asFlow().map { it.list }
    }

    override suspend fun insertQuestionSet( questionSet: QuestionSet ) {
        realm.write { copyToRealm( questionSet) }
    }




}