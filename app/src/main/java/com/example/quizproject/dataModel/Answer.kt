package com.example.quizproject.dataModel

import android.net.Uri
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
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
    var courseName : String = ""
}



class Answer (

var answerType : Char,
var answerText : String,
var answerImage : String,

)

class Chapter (

    var chapterNo : Int,
    var chapterName : String,
)


/*class  Category (
    var categoryName : String,
    var categoryDescription : String,
)*/

//////



