package com.example.quizproject.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizproject.dataModel.Category
//import com.example.quizproject.dataModel.CourseModel
import com.example.quizproject.dataRepository.MongoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CourseViewModel(

    private val repository: MongoRepository

): ViewModel() {

    //var _courseData = mutableStateOf(emptyList<CourseModel>())

    var _courseName = mutableStateOf("")


    init {
        viewModelScope.launch {
//            repository.insertCategory(category = Category().apply {
//                _id = org.mongodb.kbson.ObjectId()
//                categoryName = "aa"
//                categoryDescription = "ss"
//            })
            repository.getCategoryData().collect {
                //_courseData.value = it
                Log.d(">>>>","$it")

            }
        }
    }

    /*fun insertCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_courseName.value.isNotEmpty()) {

                *//*Log.d(">>>",_categoryName.value)
                Log.d(">>>",_categoryDescription.value)*//*
                repository.insertCategory(category = Category().apply {

                  //  courseName = this@CourseViewModel._courseName.value


                })
            }
        }
    }*/
}