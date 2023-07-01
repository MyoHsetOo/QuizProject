package com.example.quizproject.dataModel

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
//


class Category : RealmObject {


    @PrimaryKey
    var _id : ObjectId = ObjectId()

    var categoryName : String = ""
    var categoryDescription : String = ""

     var courses: RealmList<CourseModel> = realmListOf()

}


class  CourseModel() : RealmObject {

    @PrimaryKey
    var _id : ObjectId = ObjectId()
    var courseCategoryId : String = ""
    var courseName : String = ""
    var books : RealmList<BookModel> = realmListOf()



   /* constructor( id : ObjectId, courseName : String ) : this() {
        this._id = id
        this.courseName = courseName
    }*/
}

class  BookModel() : RealmObject {

    @PrimaryKey
    var _id : ObjectId = ObjectId()
    var bookCourseId : String = ""
    var bookName : String = ""
    var chapters : RealmList<ChapterModel> = realmListOf()

}

class ChapterModel() : RealmObject {

    @PrimaryKey
    var _id : ObjectId = ObjectId()
    var chapterBookId : String = ""
    var chapterName : String = ""

    var questions : RealmList<QuestionSet> = realmListOf()
}


class QuestionSet() : RealmObject {

    @PrimaryKey
    var _id : ObjectId = ObjectId()
    var questionChapterId : String = ""
    var questionNo : String = ""
    var questionType : String = ""
    var questionText : String = ""
    var questionImage : String = ""

    var answers : RealmList<AnswerModel> = realmListOf()

    var correctAnswerType : String = ""
    var solutionType : String = ""
    var solutionText : String = ""
    var solutionImage : String = ""

}

class AnswerModel( ) : RealmObject {

    @PrimaryKey
    var _id : ObjectId = ObjectId()
    var answerQuestionSetId : String = ""
    var isAnswerClick : Boolean = false
    var answerType : String = ""
    var answerOption : Char = 'A'
    var answerText : String = ""
    var answerImage : String = ""


    constructor( answerOption: Char, answerType: String ,answerText : String , answerImage: String ,isAnswerClick : Boolean ) : this() {
        this.answerOption = answerOption
        this.answerType = answerType
        this.answerText = answerText
        this.answerImage = answerImage
        this.isAnswerClick = isAnswerClick
    }

}






class Answer(

    var answerType: Char,
    var answerText: String,
    var answerImage: String,

    )

class Chapter (

    var chapterNo : Int,
    var chapterName : String,
)






