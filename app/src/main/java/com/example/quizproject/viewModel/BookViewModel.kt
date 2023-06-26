package com.example.quizproject.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizproject.dataModel.BookModel
import com.example.quizproject.dataModel.CourseModel
import com.example.quizproject.dataRepository.MongoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class BookViewModel(

    private val repository: MongoRepository

) : ViewModel() {


    var _bookData = mutableStateOf(emptyList<BookModel>())

    var _bookName = mutableStateOf("")



    init {
        viewModelScope.launch {
//            repository.insertCategory(category = Category().apply {
//                _id = org.mongodb.kbson.ObjectId()
//                categoryName = "aa"
//                categoryDescription = "ss"
//            })
            repository.getBookData().collect {
                _bookData.value = it
                Log.d(">>>>","$it")


            }

        }
    }


    fun updateBook( id : ObjectId ,chapterName : String ) {
        viewModelScope.launch(Dispatchers.IO) {

            repository.updateBook( bookModel = BookModel().apply {
                _id = id

                Log.d("IMPL id >>>>>","$_id")
                /*CourseModel(
                   courseId,
                     courseName
                )*/
                //name = this@HomeViewModel.name.value
            } , chapterName )

        }
    }



    fun insertBook( id: String ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_bookName.value.isNotEmpty()) {

                Log.d("BOokName>>>",_bookName.value)

                repository.insertBook( bookModel = BookModel().apply {
                    bookCourseId = id

                    bookName = this@BookViewModel._bookName.value
                })
            }
        }
    }


}