package com.example.quizproject.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizproject.dataModel.Category
import com.example.quizproject.dataModel.CourseModel
//import com.example.quizproject.dataModel.CourseModel
import com.example.quizproject.dataRepository.MongoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class CourseViewModel(

    private val repository: MongoRepository

): ViewModel() {

    var _courseData = mutableStateOf(emptyList<CourseModel>())

    var _courseName = mutableStateOf("")





    init {
        viewModelScope.launch {
//            repository.insertCategory(category = Category().apply {
//                _id = org.mongodb.kbson.ObjectId()
//                categoryName = "aa"
//                categoryDescription = "ss"
//            })
            repository.getCourseData().collect {
                _courseData.value = it
                Log.d(">>>>","$it")



            }

        }
    }



    fun updateCourse( id : ObjectId ,bookName : String ) {
        viewModelScope.launch(Dispatchers.IO) {

            repository.updateCourse( courseModel = CourseModel().apply {
                _id = id

                Log.d("IMPL id >>>>>","$_id")
                /*CourseModel(
                   courseId,
                     courseName
                )*/
                //name = this@HomeViewModel.name.value
            } , bookName )

        }
    }



    fun insertCourse() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_courseName.value.isNotEmpty()) {

                Log.d(">>>",_courseName.value)

                repository.insertCourse( courseModel = CourseModel().apply {
                    courseName = this@CourseViewModel._courseName.value
                })
            }
        }
    }
}