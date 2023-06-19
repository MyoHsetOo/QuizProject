package com.example.quizproject.dataModel

import android.net.Uri
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class Category : RealmObject {

    @PrimaryKey
    var categoryId : ObjectId = ObjectId.invoke()
    var categoryName : String = ""
    var categoryDescription : String = ""
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



