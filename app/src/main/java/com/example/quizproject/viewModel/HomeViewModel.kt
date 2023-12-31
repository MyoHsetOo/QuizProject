package com.example.quizproject.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizproject.dataModel.Category
import com.example.quizproject.dataModel.CourseModel
import com.example.quizproject.dataRepository.MongoRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId


class HomeViewModel (

    private val repository: MongoRepository

) : ViewModel() {

    var _categoryData = mutableStateOf(emptyList<Category>())

    var _categoryName = mutableStateOf("")

    var _categoryDescription = mutableStateOf( "")

    var _categoryId = mutableStateOf("")

    var _isClickCategory  : Category? = null

    init {
        viewModelScope.launch {
//            repository.insertCategory(category = Category().apply {
//                _id = org.mongodb.kbson.ObjectId()
//                categoryName = "aa"
//                categoryDescription = "ss"
//            })
            repository.getCategoryData().collect {
                _categoryData.value = it
                Log.d(">>>>","$it")

            }
        }

        /*for ( item in _categoryData.value){
            if ( item.isClickedCategory == true){
                _isClickCategory= item
            }
        }*/
    }


    fun updateCategory( id : ObjectId ,courseName : String ) {
        viewModelScope.launch(Dispatchers.IO) {

                repository.updateCategory( category = Category().apply {
                    _id = id

                    Log.d("IMPL id >>>>>","$_id")
                        /*CourseModel(
                           courseId,
                             courseName
                        )*/
                    //name = this@HomeViewModel.name.value
                } ,courseName)

        }
    }

    fun insertCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_categoryName.value.isNotEmpty()) {

                /*Log.d(">>>",_categoryName.value)
                Log.d(">>>",_categoryDescription.value)*/
                repository.insertCategory(category = Category().apply {
                    categoryName = this@HomeViewModel._categoryName.value
                    categoryDescription = this@HomeViewModel._categoryDescription.value

                })
            }
        }
    }





}